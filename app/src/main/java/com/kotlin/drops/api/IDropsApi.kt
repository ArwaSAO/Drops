package com.kotlin.drops.api

import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.DonorInfo
import com.kotlin.drops.model.PatientInfo
import retrofit2.Response
import retrofit2.http.*


interface IDropsApi {

    //======================  for patient Info  ==================================================//


    //get PatientInfo data model

    @GET("/PatientInfo")
    suspend fun getPatientInfo(): Response<List<PatientInfo>>

    //get PatientInfo data model by Id

    @GET("/PatientInfo/{id}")
    suspend fun getPatientInfoId(): Response<PatientInfo>

    //add PatientInfo data model

    @POST("/PatientInfo")
    suspend fun addPatientInfo(@Body patientInfo: PatientInfo): Response<PatientInfo>

    //update PatientInfo data model

    @PUT("/PatientInfo/{id}")
    suspend fun upDatePatientInfo(@Path("id")Id: String,
                                  @Body patientInfo: PatientInfo): Response<PatientInfo>

    //delete data from PatientInfo data model

    @DELETE("/PatientInfo/{id}")
    suspend fun deletePatientInfo(@Path("id")Id: String, ): Response<PatientInfo>


    //================= Donor Info =========================================================//

    //get data from DonorInfo data model

    @GET("/donorInfo")
    suspend fun getDonorInfo(): Response<List<DonorInfo>>

    //get data by Id from DonorInfo data model

    @GET("/donorInfo/{id}")
    suspend fun getDonorsId(): Response<DonorInfo>

    //add new data to DonorInfo data model

    @POST("/donorInfo")
    suspend fun addDonorInfo(@Body donorInfo: DonorInfo): Response<DonorInfo>

    // update data from DonorInfo data model

    @PUT("/donorInfo/{id}")
    suspend fun updateDonorInfo(@Path("id")Id: String,
                                @Body donorInfo: DonorInfo): Response<DonorInfo>

    // delete data from DonorInfo data model

    @DELETE("/donorInfo/{id}")
    suspend fun deleteDonorInfo(@Path("id")Id: String): Response<DonorInfo>


    //==================  Donation Info  ============================================================//

    //get data for Donations data model

    @GET("/donations")
    suspend fun getDonationsInfo(): Response<List<Donataitons>>

    //get data by Id from Donations data model

    @GET("/donations/{id}")
    suspend fun getDonationId(): Response<Donataitons>

    //add the data to Donations data model

    @POST("/donations")
    suspend fun addDonationsId(@Body donataitons: Donataitons): Response<Donataitons>

    //update Donations data model

    @PUT("/donations/{id}")
    suspend fun updateDonations(@Path("id")Id: String,
                                @Body donataitons: Donataitons): Response<Donataitons>


    //delete from Donations data model

    @DELETE("/donations/:id")
    suspend fun deleteDonations(@Path("id")Id: String): Response<Donataitons>


}