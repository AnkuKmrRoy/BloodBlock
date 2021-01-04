package com.mr.mrtestapplication.view_model

import android.os.Bundle
import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.fluperassignmet.core.BaseViewModel
import com.mr.mrtestapplication.connectivity.model.DataResponse
import com.mr.mrtestapplication.connectivity.model.ImageListData
import com.mr.mrtestapplication.data.repositories.ImageRepository
import com.roro.roroassignment.connectivity.retrofit.AppAPICallServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListViewModel(private val repository: ImageRepository) : BaseViewModel() , Observable {
     lateinit var responseData: DataResponse

     //var imageList: LiveData<List<ImageListData>>? = null

    private val imageList: MutableLiveData<ArrayList<ImageListData>> = MutableLiveData()
    var totalCount:Int = 0

    fun getServicesData(api_key:String,limit:Int,rating:String): LiveData<ArrayList<ImageListData>>?{
        AppAPICallServices.imageApiCall(api_key,limit,rating).enqueue(object : Callback<DataResponse> {

            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if(response.isSuccessful) {
                    responseData = response.body()!!
                    imageList.postValue(responseData.data)
                    totalCount = responseData.pagination.total_count
                    //getTotalCount(responseData.pagination.total_count);
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e("EXC",toString())
            }
        })

        return imageList
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

}