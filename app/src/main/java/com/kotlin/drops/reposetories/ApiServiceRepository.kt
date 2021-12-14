package com.kotlin.drops.reposetories

import android.content.Context
import androidx.room.Delete
import androidx.room.Update
import com.kotlin.drops.api.IDropsApi
import com.kotlin.drops.model.PatientsInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


const val SHARED_PREF_FILE = "Auth"
private const val BASE_URL = "https://61af59a53e2aba0017c49208.mockapi.i0"


class ApiServiceRepository(val context: Context) {


    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val retrofitApi = retrofitService.create(IDropsApi::class.java)

    //we assign shared pref to request token
    private val sharedPref = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)


    //suspend fun upDateItem(patients: PatientsInfo)



    //suspend fun deleteItem(patients: PatientsInfo)


    companion object {
        private var instance: ApiServiceRepository? = null
        fun init(context: Context) {
            if (instance == null)
                instance = ApiServiceRepository(context)

        }

        fun get(): ApiServiceRepository {
            return instance ?: throw Exception("ApiServiceRepository must be initialized")
        }
    }




}