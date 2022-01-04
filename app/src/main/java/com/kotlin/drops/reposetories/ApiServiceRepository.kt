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



    //we assign shared pref
    private val sharedPref = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)


    //====== functionality of Patient Info =====================================================//


    //get PatientInfo data model
    suspend fun getPatientInfo() = retrofitApi.getPatientInfo()

    //get PatientInfo data model by Id
    suspend fun getPatientInfoId() = retrofitApi.getPatientInfoId()

    //add PatientInfo data model
    suspend fun addPatientInfo(patientInfo: PatientInfo)
    = retrofitApi.addPatientInfo(patientInfo)

    //update PatientInfo data model
    suspend fun upDatePatientInfo(id:String, patientInfo: PatientInfo)
    = retrofitApi.upDatePatientInfo(id, patientInfo)

    //delete data from PatientInfo data model
    suspend fun deletePatientInfo(id: String) = retrofitApi.deletePatientInfo(id)


    //============ Functionality of Donor Info =================================================//

    //get data from DonorInfo data model
    suspend fun getDonorInfo() = retrofitApi.getDonorInfo()

    //get data by Id from DonorInfo data model
    suspend fun getDonorsId() = retrofitApi.getDonorsId()

    //add new data to DonorInfo data model
    suspend fun addDonorInfo(donorInfo: DonorInfo)
    = retrofitApi.addDonorInfo(donorInfo)

    // update data from DonorInfo data model
    suspend fun updateDonorInfo(id:String, donorInfo: DonorInfo)
    = retrofitApi.updateDonorInfo(id,donorInfo)

    // delete data from DonorInfo data model
    suspend fun deleteDonorInfo(id: String) = retrofitApi.deleteDonorInfo(id)


    //=============  Functionality of Donations Info ===========================================//

    //get data for Donations data model
    suspend fun getDonationsInfo() = retrofitApi.getDonationsInfo()

    //get data by Id from Donations data model
    suspend fun getDonationId() = retrofitApi.getDonationId()

    //add the data to Donations data model
    suspend fun addDonationsId(donataitons: Donataitons)
    = retrofitApi.addDonationsId(donataitons)

    //update Donations data model
    suspend fun updateDonations(id: String, donataitons: Donataitons)
    = retrofitApi.updateDonations(id,donataitons)

    //delete from Donations data model
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