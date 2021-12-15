package com.kotlin.drops.model


import com.google.gson.annotations.SerializedName

data class DonerInfo(
    @SerializedName("bloodGroup")
    val bloodGroup: String,
    @SerializedName("conatctNumber")
    val conatctNumber: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("UserId")
    val userId: String
)