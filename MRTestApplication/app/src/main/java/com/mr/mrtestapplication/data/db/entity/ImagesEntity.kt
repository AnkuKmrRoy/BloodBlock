package com.mr.mrtestapplication.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MrTestAssignment")
class ImagesEntity(@PrimaryKey
                       @ColumnInfo(name = "id") val id: String,
                       @ColumnInfo(name = "url") var image_url: String
)