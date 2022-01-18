package com.kotlin.drops.reposetories

import android.content.Context
import com.kotlin.drops.api.IDropsApi
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.DonorInfo
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.view.main.SHARED_PREF_FILE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://61af59a53e2aba0017c49208.mockapi.io"


class ApiServiceRepository(val context: Context) {


    /***
     *
     * To work with Retrofit you basically need the following three classes:
    Model class which is used as a JSON model
    Interfaces that define the possible HTTP operations
    Retrofit.Builder class - Instance which uses the interface and the Builder API to allow defining the URL end point for the HTTP operations.
     * */

    // Retrofit.Builder
    // And we need to specify a factory for deserializing the response using the Gson library

    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //  Builder API

    private val retrofitApi = retrofitService.create(IDropsApi::class.java)


    //====== functionality of Patient Info =====================================================//


    //get PatientInfo data model
    suspend fun getPatientInfo() = retrofitApi.getPatientInfo()


    //add PatientInfo data model
    suspend fun addPatientInfo(patientInfoBody: PatientInfo)
    = retrofitApi.addPatientInfo(patientInfoBody)

    //update PatientInfo data model
    suspend fun upDatePatientInfo(id:String, patientInfoBody: PatientInfo)
    = retrofitApi.upDatePatientInfo(id, patientInfoBody)

    //delete data from PatientInfo data model
    suspend fun deletePatientInfo(id: String) = retrofitApi.deletePatientInfo(id)


    //============ Functionality of Donor Info =================================================//

    //get data from DonorInfo data model
    suspend fun getDonorInfo() = retrofitApi.getDonorInfo()

    //add new data to DonorInfo data model
    suspend fun addDonorInfo(donorInfoBody: DonorInfo)
    = retrofitApi.addDonorInfo(donorInfoBody)

    // update data from DonorInfo data model
    suspend fun updateDonorInfo(id:String, donorInfoBody: DonorInfo)
    = retrofitApi.updateDonorInfo(id,donorInfoBody)

    // delete data from DonorInfo data model
    suspend fun deleteDonorInfo(id: String) = retrofitApi.deleteDonorInfo(id)

    //=============  Functionality of Donations Info ===========================================//

    //get data for Donations data model
    suspend fun getDonationsInfo() = retrofitApi.getDonationsInfo()

    //add the data to Donations data model
    suspend fun addDonations(donataitonsBody: Donataitons)
    = retrofitApi.addDonations(donataitonsBody)

    //update Donations data model
    suspend fun updateDonations(id: String, donataitons: Donataitons)
    = retrofitApi.updateDonations(id,donataitons)

    //delete from Donations data model
    suspend fun deleteDonations(id: String) = retrofitApi.deleteDonations(id)


    //===========================================================================================//


    // to initialize and get the repository we use the companion object
    //singleton (single object)

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