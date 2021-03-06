package com.encora.bloodblock.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LoginUsers")
data class LoginData(@PrimaryKey(autoGenerate = false)
                     @ColumnInfo(name = "mobile_no") val mob_no: String,
                     @ColumnInfo(name = "user_type") val user_type: String

)