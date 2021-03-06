package com.encora.bloodblock.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospitalData")
data class HospitalData(@PrimaryKey(autoGenerate = false)
                        @ColumnInfo (name = "patientID") val patientID:String,
                        @ColumnInfo (name = "hospitalName") val hospitalName: String,
                        @ColumnInfo (name = "patientName") val patientName:String,
                        @ColumnInfo (name = "patientMobile") val patientMobile:String,
                        @ColumnInfo (name = "fatherName") val fatherName:String,
                        @ColumnInfo (name = "address") val address:String,
                        @ColumnInfo (name = "disease") val disease:String,
                        @ColumnInfo (name = "needed_blood_group") val needed_blood_group: String,
                        @ColumnInfo (name = "gender") val gender:String,
                        @ColumnInfo (name = "remark") val remark:String?,
                        @ColumnInfo (name = "status") val status:Boolean?,
                        @ColumnInfo (name = "qr_code") val qr_code:String?
)