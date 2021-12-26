package com.kotlin.drops.api

import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.DonorInfo
import com.kotlin.drops.model.PatientInfo
import retrofit2.Response
import retrofit2.http.*


interface IDropsApi {

    //======================  for patient Info  ==================================================//


    @GET("/PatientInfo")
    suspend fun getPatientInfo(): Response<PatientInfo>

    @GET("/PatientInfo/{id}")
    suspend fun getPatientInfoId(): Response<PatientInfo>

    @POST("/PatientInfo")
    suspend fun addPatientInfo(@Body patientInfo: PatientInfo): Response<PatientInfo>

    @PUT("/PatientInfo/{id}")
    suspend fun upDatePatientInfo(@Path("id")Id: String,
                                  @Body patientInfo: PatientInfo): Response<PatientInfo>

    @DELETE("/PatientInfo/{id}")
    suspend fun deletePatientInfo(@Path("id")Id: String, ): Response<PatientInfo>


    //================= Donor Info =========================================================//


    @GET("/donorInfo")
    suspend fun getDonorInfo(): Response<DonorInfo>

    @GET("/donorInfo/{id}")
    suspend fun getDonorsId(): Response<DonorInfo>

    @POST("/donorInfo")
    suspend fun addDonorInfo(@Body donorInfo: DonorInfo): Response<DonorInfo>

    @PUT("/donorInfo/{id}")
    suspend fun updateDonorInfo(@Path("id")Id: String,
                                @Body donorInfo: DonorInfo): Response<DonorInfo>

    @DELETE("/donorInfo/{id}")
    suspend fun deleteDonorInfo(@Path("id")Id: String): Response<DonorInfo>


    //==================  Donation Info  ============================================================//


    @GET("/donations")
    suspend fun getDonationsInfo(): Response<Donataitons>

    @GET("/donations/{id}")
    suspend fun getDonationId(): Response<Donataitons>

    @POST("/donations")
    suspend fun addDonationsId(@Body donataitons: Donataitons): Response<Donataitons>

    @PUT("/donations/{id}")
    suspend fun updateDonations(@Path("id")Id: String,
                                @Body donataitons: Donataitons): Response<Donataitons>

    @DELETE("/donations/:id")
    suspend fun deleteDonations(@Path("id")Id: String): Response<Donataitons>


}