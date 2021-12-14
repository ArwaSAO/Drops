package com.kotlin.drops.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlin.drops.model.PatientsInfo

@Dao
interface DropsDao {


//
//    @Insert
//    suspend fun addItem(patientsInfo: PatientsInfo)
//
//    @Query("SELECT * FROM patientsinfo")
//    fun addItem(): LiveData<List<PatientsInfo>>


    @Update
    suspend fun upDateItem(patients: PatientsInfo)


    @Delete
    suspend fun deleteItem(patients: PatientsInfo)


}