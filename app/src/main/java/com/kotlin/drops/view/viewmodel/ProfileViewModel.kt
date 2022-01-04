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
    val getDonorInfoLiveData = MutableLiveData<List<DonorInfo>>()

    //for Error live data
    val donorInfoErrorLiveData = MutableLiveData<String>()

    //select single item from the live data
    var selectedItemMutableLiveData = MutableLiveData<DonorInfo>()

    //edit live data
    var editDonorInfoLiveData = MutableLiveData<DonorInfo>()


    // get DonorInfo from Api
    fun callDonorInfo() {

        // we need Scope with the suspend function
        //viewModelScope -->> the Scope  end after the function end

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.getDonorInfo()

                if (response.isSuccessful) {
                    response.body()?.run {
                        getDonorInfoLiveData.postValue(this)
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



    // edit DonorInfo Api
    fun editDonorInfo(donorInfoBody: DonorInfo){

        // we need Scope with the suspend function
        //viewModelScope -->> the Scope  end after the function end

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.updateDonorInfo(donorInfoBody.id, donorInfoBody)

                if (response.isSuccessful) {
                    response.body()?.run {
                        getDonorInfoLiveData.postValue(listOf(this))
                        Log.d(TAG, this.toString())
//                        editDonorInfoLiveData.postValue("Successful")
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