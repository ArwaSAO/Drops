package com.kotlin.drops.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.reposetories.ApiServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {


    private val apiRepo = ApiServiceRepository.get()
    val patientInfoLiveData = MutableLiveData<List<PatientInfo>>()
    val patientInfoErrorLiveData = MutableLiveData<String>()



    fun callPatientList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.getDonationsInfo()

                if (response.isSuccessful) {
                    response.body()?.run {
                        patientInfoLiveData.postValue(this)
                        Log.d(TAG, this.toString())
                    }
                } else {
                    Log.d(TAG, response.message())
                    patientInfoErrorLiveData.postValue(response.message())
                }

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                patientInfoErrorLiveData.postValue(e.message.toString())

            }

        }
    }

    }