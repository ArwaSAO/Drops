package com.kotlin.drops.api

import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.DonorInfo
import com.kotlin.drops.model.PatientInfo
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT


interface IDropsApi {

    //======================  for patient Info  ==================================================//


    @GET("/PatientInfo")
    suspend fun getPatientInfo(): Response<PatientInfo>

    @GET("/PatientInfo/:id")
    suspend fun getPatientInfoId(): Response<PatientInfo>

    @POST("/PatientInfo")
    suspend fun addPatientInfo(): Response<PatientInfo>

    @PUT("/PatientInfo/:id")
    suspend fun upDatePatientInfo(): Response<PatientInfo>

    @DELETE("/PatientInfo/:id")
    suspend fun deletePatientInfo(): Response<PatientInfo>


    //================= Donor Info =========================================================//


    @GET("/donorInfo")
    suspend fun getDonorInfo(): Response<DonorInfo>

    @GET("/donorInfo/:id")
    suspend fun getDonorsId(): Response<DonorInfo>

    @POST("/donorInfo")
    suspend fun addDonorInfo(): Response<DonorInfo>

    @PUT("/donorInfo/:id")
    suspend fun updateDonorInfo(): Response<DonorInfo>

    @DELETE("/donorInfo/:id")
    suspend fun deleteDonerInfo(): Response<DonorInfo>


    //==================  Donation Info  ============================================================//


    @GET("/donations")
    suspend fun getDonationsInfo(): Response<Donataitons>

    @GET("/donations/:id")
    suspend fun getDonationId(): Response<Donataitons>

    @POST("/donations")
    suspend fun addDonationsId(): Response<Donataitons>

    @PUT("/donations/:id")
    suspend fun updateDonations(): Response<Donataitons>

    @DELETE("/donations/:id")
    suspend fun deleteDonations(): Response<Donataitons>


}