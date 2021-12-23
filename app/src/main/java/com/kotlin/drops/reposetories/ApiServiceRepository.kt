package com.kotlin.drops.reposetories

import android.content.Context
import com.kotlin.drops.api.IDropsApi
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.DonorInfo
import com.kotlin.drops.model.PatientInfo
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import java.lang.Exception


const val SHARED_PREF_FILE = "Auth"
private const val BASE_URL = "https://61af59a53e2aba0017c49208.mockapi.io"


class ApiServiceRepository(val context: Context) {


    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val retrofitApi = retrofitService.create(IDropsApi::class.java)


    //we assign shared pref to request token
    private val sharedPref = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)


    //====== functionality of Patient Info =====================================================//


    suspend fun getPatientInfo() = retrofitApi.getPatientInfo()

    suspend fun getPatientInfoId() = retrofitApi.getPatientInfoId()

    suspend fun addPatientInfo() = retrofitApi.addPatientInfo()

    suspend fun upDatePatientInfo() = retrofitApi.upDatePatientInfo()

    suspend fun deletePatientInfo() = retrofitApi.deletePatientInfo()


    //============ Functionality of Donor Info =================================================//


    suspend fun getDonorInfo() = retrofitApi.getDonorInfo()

    suspend fun getDonorsId() = retrofitApi.getDonorsId()

    suspend fun addDonorInfo() = retrofitApi.addDonorInfo()

    suspend fun updateDonorInfo() = retrofitApi.updateDonorInfo()

    suspend fun deleteDonorInfo() = retrofitApi.deleteDonorInfo()


    //=============  Functionality of Donations Info ===========================================//

    suspend fun getDonationsInfo() = retrofitApi.getDonationsInfo()

    suspend fun getDonationId() = retrofitApi.getDonationId()

    suspend fun addDonationsId() = retrofitApi.addDonationsId()

    suspend fun updateDonations() = retrofitApi.updateDonations()

    suspend fun deleteDonations() = retrofitApi.deleteDonations()


    //===========================================================================================//



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