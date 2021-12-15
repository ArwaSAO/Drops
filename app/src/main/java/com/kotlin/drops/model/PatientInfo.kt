package com.kotlin.drops.model


import com.google.gson.annotations.SerializedName

data class PatientInfo(
    @SerializedName("bloodGroup")
    val bloodGroup: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("hospital")
    val hospital: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("left")
    val left: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("need")
    val need: Int,
    @SerializedName("userId")
    val userId: Int
)