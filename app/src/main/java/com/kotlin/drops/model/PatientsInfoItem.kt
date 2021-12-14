package com.kotlin.drops.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity
data class PatientsInfoItem(


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
    val userId: Int,

    var isUpdate: Boolean = false

)