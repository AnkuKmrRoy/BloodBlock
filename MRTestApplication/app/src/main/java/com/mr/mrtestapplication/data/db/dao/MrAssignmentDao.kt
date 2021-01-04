package com.mr.mrtestapplication.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mr.mrtestapplication.data.db.entity.ImagesEntity

@Dao
interface MrAssignmentDao {

    @Query("SELECT * FROM MrTestAssignment")
     fun getImages(): LiveData<List<ImagesEntity>>

    @Insert
     suspend fun insertImages(imageList: List<ImagesEntity>)


}

