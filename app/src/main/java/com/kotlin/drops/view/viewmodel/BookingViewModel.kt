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
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "BokkingViewModel"


class BookingViewModel: ViewModel() {



    private val apiRepo = ApiServiceRepository.get()

    //for the live data
    val getDonationsLiveData = MutableLiveData<List<Donataitons>>()

    // for the Error live data
    val donationsErrorLiveData = MutableLiveData<String>()

    //select single item from the live data
    var selectedItemMutableLiveData = MutableLiveData<Donataitons>()

    // for add new to the live data
    var addDonationsLiveData = MutableLiveData<String>()

    var latitude = "  "
    var longitude = " "
    var date = " "
    var fullName = " "
    var hospital = " "
    var id = " "
    var location = " "
    var time = " "
    var userId = " "


    // get Donations data model
    fun callDonations() {

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
    fun addDonations(donataitonsBody: Donataitons) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.addDonationsId(
                    Donataitons (donataitonsBody.date, donataitonsBody.fullName, donataitonsBody.hospital, donataitonsBody.id,
                    donataitonsBody.latitude, donataitonsBody.location, donataitonsBody.longitude, donataitonsBody.time,
                    donataitonsBody.userId))

                if (response.isSuccessful) {
                    response.body()?.run {
                        getDonationsLiveData.postValue(listOf(this))
                        Log.d(TAG, this.toString())
                        addDonationsLiveData.postValue("Successful")
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



}