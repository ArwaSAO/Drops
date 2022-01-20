package com.kotlin.drops.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.UserInfo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.kotlin.drops.model.UserProfile
import com.kotlin.drops.reposetories.UserProfileRepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "UserProfileViewModel"

class UserProfileViewModel : ViewModel() {

    val saveUserLiveData = MutableLiveData<UserProfile>()
    val getUserLiveData = MutableLiveData<UserProfile>()
    val userErrorLiveData = MutableLiveData<String>()


    private val apiRepo = UserProfileRepositoryService.get()
    private var firestore: FirebaseFirestore

    //=============================================================================================//

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    }

    //==============================for saving userinfo  data===========================================//


    fun save(userProfile: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                apiRepo.saveProfile(userProfile).addOnSuccessListener {
                    Log.d(TAG, "document saved")
                    saveUserLiveData.postValue(userProfile)
                }.addOnFailureListener {
                    userErrorLiveData.postValue("")
                    Log.d(TAG, it.message.toString())


                }

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                userErrorLiveData.postValue(e.message.toString())
            }
        }
    }

    //=============================For get user info data=========================================//


    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                apiRepo.getUser().addOnSuccessListener { documentSnapshot ->
                    val user = documentSnapshot.toObject<UserProfile>(UserProfile::class.java)
                    Log.d("Firebase", "document saved")
                }.addOnFailureListener {
                    userErrorLiveData.postValue("")
                    Log.d("Firebase", it.message.toString())
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                userErrorLiveData.postValue(e.message.toString())
            }
        }
    }
}

//================================================================================================//
