package com.kotlin.drops.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.drops.model.DonorInfo
import com.kotlin.drops.reposetories.ApiServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


private const val TAG = "ProfileViewModel"

class ProfileViewModel : ViewModel() {

    private val apiRepo = ApiServiceRepository.get()

    //for the live data
    val donorInfoLiveData = MutableLiveData<List<DonorInfo>>()

    //for Error live data
    val donorInfoErrorLiveData = MutableLiveData<String>()

    //select single item from the live data
    var selectedItemMutableLiveData = MutableLiveData<DonorInfo>()

    //edit live data
    var editDonorInfoLiveData = MutableLiveData<String>()

    // add live data
    var ddDonorInfoLiveData = MutableLiveData<String>()


    // get DonorInfo from Api
    fun callDonorInfo() {

        // we need Scope with the suspend function
        //viewModelScope -->> the Scope  end after the function end

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.getDonorInfo()

                if (response.isSuccessful) {
                    response.body()?.run {
//                        donorInfoLiveData.postValue(this)

                        // return data inside to strings
                        Log.d(TAG, this.toString())
                    }
                } else {
                    Log.d(TAG, response.message())
                    donorInfoErrorLiveData.postValue(response.message())
                }

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                donorInfoErrorLiveData.postValue(e.message.toString())

            }

        }
    }


    // add new to Donations data model
    fun addDonorInfo(donorInfoBody: DonorInfo) {

        // Use try and catch for handling http exceptions

        viewModelScope.launch(Dispatchers.IO) {

            // Use try and catch for handling http exceptions

            try {

                // Calling the API Methods and handles the result

                val response = apiRepo.addDonorInfo(
                    DonorInfo(
                        donorInfoBody.bloodGroup,
                        donorInfoBody.age,
                        donorInfoBody.name,
                        donorInfoBody.conatctNumber,
                        donorInfoBody.id,
                        donorInfoBody.userId
//                     ,FirebaseAuth.getInstance().currentUser!!.uid,
                    )
                )

                if (response.isSuccessful) {
                    Log.d(TAG, "Success")
                    response.body()?.run {
                        Log.d(TAG, "DonorInfo: $this")
                        ddDonorInfoLiveData.postValue("Success")

                    }
                } else {
                    Log.d(TAG, response.message())
                    donorInfoErrorLiveData.postValue(response.message())
                }

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                donorInfoErrorLiveData.postValue(e.message.toString())

            }

        }
    }

    // Edit Donor Info data from Api
    fun editDonorInfo(donorInfoBody: DonorInfo) {

        // we need Scope with the suspend function
        //viewModelScope -->> the Scope  end after the function end

        viewModelScope.launch(Dispatchers.IO) {

            //send request
            try {

                val response = apiRepo.updateDonorInfo(donorInfoBody.id,donorInfoBody)

                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        editDonorInfoLiveData.postValue("Successful")
                    }
                } else {
                    Log.d(TAG, response.message())
                    donorInfoErrorLiveData.postValue(response.message())
                }

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                donorInfoErrorLiveData.postValue(e.message.toString())

            }

        }
    }


}