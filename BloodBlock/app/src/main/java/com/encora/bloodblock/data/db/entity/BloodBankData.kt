package com.encora.bloodblock.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blood_bank_data")
data class BloodBankData(@PrimaryKey(autoGenerate = false)
                         @ColumnInfo (name = "patientID") val PatientID :String,
                         @ColumnInfo (name = "qr_code") val qr_code: String
)