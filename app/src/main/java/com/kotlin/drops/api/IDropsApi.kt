package com.kotlin.drops.api

import com.kotlin.drops.model.PatientInfoDataModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface IDropsApi {

    @GET("/Common/Product/GetProductsForSell?page=0")
    suspend fun getProducts(
        @Header("Authorization") token: String
    ) : Response<PatientInfoDataModelItem>
}