package com.kotlin.drops.database

import androidx.room.*
import com.kotlin.drops.model.DonerInfo

@Dao
interface DropsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(patientInfo: PatientInfo)

    @Query("SELECT * FROM donerinfo")
    suspend fun getItems(): List<DonerInfo>

    @Query("DELETE FROM donerinfo")
    suspend fun deleteItem()


    @Update
    suspend fun updatePhoto(donerInfo: DonerInfo)



}