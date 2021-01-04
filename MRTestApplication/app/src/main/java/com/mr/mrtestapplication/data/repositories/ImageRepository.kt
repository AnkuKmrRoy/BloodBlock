package com.mr.mrtestapplication.data.repositories

import androidx.lifecycle.LiveData
import com.mr.mrtestapplication.data.db.dao.MrAssignmentDao
import com.mr.mrtestapplication.data.db.entity.ImagesEntity

class ImageRepository(private val dao: MrAssignmentDao) {

      fun  getServicesUsingDate(date:String): LiveData<List<ImagesEntity>>? {
        val image_list =dao.getImages()
        return image_list
    }

    suspend fun insertServicesListFromApiResponse(imageList:List<ImagesEntity>){
        dao.insertImages(imageList)
    }

}