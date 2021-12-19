package com.kotlin.drops.model


import com.google.gson.annotations.SerializedName

data class Donataitons(
    @SerializedName("date")
    val date: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("hospital")
    val hospital: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("Location")
    val location: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("userId")
    val userId: String
)