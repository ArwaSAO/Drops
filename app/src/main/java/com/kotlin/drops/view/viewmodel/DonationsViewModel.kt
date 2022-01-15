package com.kotlin.drops.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.reposetories.ApiServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "DonationsViewModel"

class DonationsViewModel: ViewModel() {

    private val apiRepo = ApiServiceRepository.get()

    //for the live data
    val getDonationsLiveData = MutableLiveData<List<Donataitons>>()

    //for Error live data
    val donationsErrorLiveData = MutableLiveData<String>()

    //select single item from the live data
    var selectedItemMutableLiveData = MutableLiveData<Donataitons>()

    // add donations live data
    var addDonationsLiveData = MutableLiveData<String>()

    // delete from live data
    var deleteDonationsLiveData = MutableLiveData<String>()

    //edit live data
    var editDonationsLiveData = MutableLiveData<String>()







    // get donations from data model

    fun callDonationsList() {

        // we need Scope with the suspend function
        //viewModelScope -->> the Scope  end after the function end

        viewModelScope.launch(Dispatchers.IO) {
            // send request
            try {
                val response = apiRepo.getDonationsInfo()

                if (response.isSuccessful) {
                    response.body()?.run {
                        getDonationsLiveData.postValue(this)
                        Log.d(TAG, this.toString())
                    }
                } else {
                    Log.d(TAG, response.message())
                    donationsErrorLiveData.postValue(response.message())
                }

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                donationsErrorLiveData.postValue(e.message.toString())

            }

        }
    }

    // add new to Donations data model
    fun addDonations(donations: PatientInfo,date:String, time:String) {

        // Coroutines Dispatchers

        viewModelScope.launch(Dispatchers.IO) {
            // Use try and catch for handling http exceptions

            try {

                // Calling the API Methods and handles the result

                val response = apiRepo.addDonations(
                    Donataitons (
                        date,
                        donations.fullName,
                        donations.hospital,
                        donations.id,
                        donations.latitude,
                        donations.location,
                        donations.longitude,
                        time,
                        FirebaseAuth.getInstance().currentUser!!.uid,
                    )
                )

//                donations.fullName,
//                donations.hospital,
//                donations.id,
//                donations.latitude,
//                donations.location,
//                donations.longitude,
//                donations.diagnosis,
//                time,
//                date

                if (response.isSuccessful) {
                    Log.d(TAG,"Success")
                    response.body()?.run {
                        Log.d(TAG, "Donataitions: $this")
                        addDonationsLiveData.postValue("Success")
                    }


                    } else {
                    Log.d(TAG, response.message())
                    donationsErrorLiveData.postValue(response.message())
                }

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                donationsErrorLiveData.postValue(e.message.toString())

            }

        }
    }



    // Edit donations data from Api
    fun editDonation(donataitonsBody: Donataitons) {

        // we need Scope with the suspend function
        //viewModelScope -->> the Scope  end after the function end

        viewModelScope.launch(Dispatchers.IO) {

            //send request
            try {

                val response = apiRepo.updateDonations(donataitonsBody.id,donataitonsBody)

                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        editDonationsLiveData.postValue("Successful")
                    }
                } else {
                    Log.d(TAG, response.message())
                    donationsErrorLiveData.postValue(response.message())
                }

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                donationsErrorLiveData.postValue(e.message.toString())

            }

        }
    }

    // delete Donations from Api

    fun deleteDonations(donataitons: Donataitons){

        // we need Scope with the suspend function
        //viewModelScope -->> the Scope  end after the function end

        viewModelScope.launch(Dispatchers.IO) {


            try {

                val response = apiRepo.deleteDonations(donataitons.id)


                if (response.isSuccessful) {
                    Log.d(TAG,"Success")
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        deleteDonationsLiveData.postValue("Successful")
                    }
                } else {
                    Log.d(TAG, response.message())
                    Log.d(TAG,"fail-${response.message()}")
                    donationsErrorLiveData.postValue(response.message())
                }

            }catch (e:Exception){

                Log.d(TAG,e.message.toString())
                donationsErrorLiveData.postValue(e.message.toString())

            }


        }
    }




}


