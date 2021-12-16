package com.kotlin.drops.api

import com.kotlin.drops.model.DonerInfo
import retrofit2.Response
import retrofit2.http.GET




interface IDropsApi {

    //======================  for patient Info  ==================================================//


    @GET("/PatientInfo")
    suspend fun getPatientInfo(): Response<PatientInfo>



    @GET("/donorInfo")
    suspend fun getDonorInfo(): Response<DonerInfo>




}