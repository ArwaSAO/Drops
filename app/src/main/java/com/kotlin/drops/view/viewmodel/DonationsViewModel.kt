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
    val donationsLiveData = MutableLiveData<List<Donataitons>>()
    val donationsErrorLiveData = MutableLiveData<String>()
    var selectedItemMutableLiveData = MutableLiveData<Donataitons>()
    var deletLiveData = MutableLiveData<String>()
    var editLiveData = MutableLiveData<String>()


    fun callDonations(donataitons: Donataitons) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.getDonationsInfo()

                if (response.isSuccessful) {
                    response.body()?.run {
                        donationsLiveData.postValue(this)
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

    fun editDonation(donataitonsBody: Donataitons) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.updateDonations(donataitonsBody.id, donataitonsBody)

                if (response.isSuccessful) {
                    response.body()?.run {
                        donationsLiveData.postValue(listOf(this))
                        Log.d(TAG, this.toString())
                        editLiveData.postValue("Successful")
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

    fun deleteDonations(donataitons: Donataitons){
        viewModelScope.launch(Dispatchers.IO) {


                val response = apiRepo.deleteDonations(donataitons.id)

                if (response.isSuccessful) {
                    response.body()?.run {
                        donationsLiveData.postValue(listOf(this))
                        Log.d(TAG, this.toString())
                        deletLiveData.postValue("Successful")
                    }
                } else {
                    Log.d(TAG, response.message())
                    donationsErrorLiveData.postValue(response.message())
                }



        }
    }


}


