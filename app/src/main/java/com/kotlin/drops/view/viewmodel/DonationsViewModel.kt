package com.kotlin.drops.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.drops.model.Donataitons
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

    // delete from live data
    var deleteDonationsLiveData = MutableLiveData<String>()

    //edit live data
    var editDonationsLiveData = MutableLiveData<String>()


    var latitude = "  "
    var longitude = " "
    var date = " "
    var fullName = " "
    var hospital = " "
    var id = " "
    var location = " "
    var time = " "
    var userId = " "


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
                        getDonationsLiveData.postValue(listOf(this))
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


                val response = apiRepo.deleteDonations(donataitons.id)

                if (response.isSuccessful) {
                    response.body()?.run {
                        getDonationsLiveData.postValue(listOf(this))
                        Log.d(TAG, this.toString())
                        deleteDonationsLiveData.postValue("Successful")
                    }
                } else {
                    Log.d(TAG, response.message())
                    donationsErrorLiveData.postValue(response.message())
                }



        }
    }


}


