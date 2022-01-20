package com.kotlin.drops.api

import com.kotlin.drops.model.Donataitons
//import com.kotlin.drops.model.DonorInfo
import com.kotlin.drops.model.PatientInfo
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface IDropsApi {

    //======================  for patient Info  ==================================================//


    //get PatientInfo data model

    @GET("/PatientInfo")
    suspend fun getPatientInfo(): Response<List<PatientInfo>>


    //add PatientInfo data model

    @POST("/PatientInfo")
    suspend fun addPatientInfo(@Body patientInfoBody: PatientInfo): Response<ResponseBody>

    //update PatientInfo data model

    @PUT("/PatientInfo/{id}")
    suspend fun upDatePatientInfo(@Path("id")Id: String,
                                  @Body patientInfoBody: PatientInfo): Response<PatientInfo>

    //delete data from PatientInfo data model

    @DELETE("/PatientInfo/{id}")
    suspend fun deletePatientInfo(@Path("id")Id: String, ): Response<ResponseBody>


    //==================  Donation Info  ============================================================//

    //get data for Donations data model

    @GET("/donations")
    suspend fun getDonationsInfo(): Response<List<Donataitons>>


    //add the data to Donations data model

    @POST("/donations")
    suspend fun addDonations(@Body donataitonsBody: Donataitons): Response<ResponseBody>



    //update Donations data model

    @PUT("/donations/{id}")
    suspend fun updateDonations(@Path("id")Id: String,
                                @Body donataitonsBody: Donataitons): Response<Donataitons>


    //delete from Donations data model

    @DELETE("/donations/{id}")
    suspend fun deleteDonations(@Path("id")Id: String): Response<ResponseBody>





}