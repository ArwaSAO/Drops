package com.kotlin.drops.api

import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.DonorInfo
import com.kotlin.drops.model.PatientInfo
import retrofit2.Response
import retrofit2.http.GET


interface IDropsApi {

    //======================  for patient Info  ==================================================//


    @GET("/PatientInfo")
    suspend fun getPatientInfo(): Response<PatientInfo>


    //=================  Donations Info =========================================================//



    @GET("/donations")
    suspend fun getDonationsInfo(): Response<Donataitons>



    //==================  DonorInfo  ============================================================//


    @GET("/donorInfo")
    suspend fun getDonorInfo(): Response<DonorInfo>


}