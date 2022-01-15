package com.kotlin.drops.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.reposetories.ApiServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {


    private val apiRepo = ApiServiceRepository.get()

    //for the live data
    val getPatientInfoLiveData = MutableLiveData<List<PatientInfo>>()

    //for Error live data
    val patientInfoErrorLiveData = MutableLiveData<String>()

    //select single item from the live data
    var selectedItemMutableLiveData = MutableLiveData<PatientInfo>()



    // get PatientInfo from Api
    fun callPatientList() {

        // we need Scope with the suspend function
        //viewModelScope -->> the Scope  end after the function end
        viewModelScope.launch(Dispatchers.IO) {

            // send request
            try {
                val response = apiRepo.getPatientInfo()

                if (response.isSuccessful) {
                    response.body()?.run {
                        getPatientInfoLiveData.postValue(this)
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