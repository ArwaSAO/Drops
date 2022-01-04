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
    val getDonorInfoLiveData = MutableLiveData<List<DonorInfo>>()
    val donorInfoErrorLiveData = MutableLiveData<String>()
    var selectedItemMutableLiveData = MutableLiveData<DonorInfo>()
    var editDonorInfoLiveData = MutableLiveData<DonorInfo>()


    fun callDonorInfo() {
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

    fun editDonorInfo(donorInfoBody: DonorInfo){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.updateDonorInfo(donorInfoBody.id, donorInfoBody)

                if (response.isSuccessful) {
                    response.body()?.run {
                        getDonorInfoLiveData.postValue(listOf(this))
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