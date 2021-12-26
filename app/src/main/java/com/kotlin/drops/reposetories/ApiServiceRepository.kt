package com.kotlin.drops.reposetories

import android.content.Context
import com.kotlin.drops.api.IDropsApi
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.DonorInfo
import com.kotlin.drops.model.PatientInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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

    suspend fun addPatientInfo(patientInfo: PatientInfo)
    = retrofitApi.addPatientInfo(patientInfo)

    suspend fun upDatePatientInfo(id:String, patientInfo: PatientInfo)
    = retrofitApi.upDatePatientInfo(id, patientInfo)

    suspend fun deletePatientInfo(id: String) = retrofitApi.deletePatientInfo(id)


    //============ Functionality of Donor Info =================================================//


    suspend fun getDonorInfo() = retrofitApi.getDonorInfo()

    suspend fun getDonorsId() = retrofitApi.getDonorsId()

    suspend fun addDonorInfo(donorInfo: DonorInfo)
    = retrofitApi.addDonorInfo(donorInfo)

    suspend fun updateDonorInfo(id:String, donorInfo: DonorInfo)
    = retrofitApi.updateDonorInfo(id,donorInfo)

    suspend fun deleteDonorInfo(id: String) = retrofitApi.deleteDonorInfo(id)


    //=============  Functionality of Donations Info ===========================================//

    suspend fun getDonationsInfo() = retrofitApi.getDonationsInfo()

    suspend fun getDonationId() = retrofitApi.getDonationId()

    suspend fun addDonationsId(donataitons: Donataitons)
    = retrofitApi.addDonationsId(donataitons)

    suspend fun updateDonations(id: String, donataitons: Donataitons)
    = retrofitApi.updateDonations(id,donataitons)

    suspend fun deleteDonations(id: String) = retrofitApi.deleteDonations(id)


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