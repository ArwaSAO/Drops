package com.kotlin.drops.database

import androidx.room.Database
import com.kotlin.drops.model.PatientsInfoItem


@Database(entities = [PatientsInfoItem::class], version = 3)

abstract class DropsDataBase() {

    abstract fun dropDao(): DropsDao

}