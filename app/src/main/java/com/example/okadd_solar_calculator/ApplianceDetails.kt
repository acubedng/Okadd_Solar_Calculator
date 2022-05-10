package com.example.okadd_solar_calculator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appliance_table")
data class ApplianceDetails(
    @PrimaryKey
    @ColumnInfo(name = "applianceName")
                       val applianceName:String,
    @ColumnInfo(name= "applianceRating")
    val applianceRating: Int,
    @ColumnInfo(name = "usageHour")
    val usageHour: Int,
    @ColumnInfo(name = "quantity")
    val quantity: Int
)