package com.roro.roroassignment.connectivity.retrofit

import com.mr.mrtestapplication.connectivity.model.DataResponse
import com.mr.mrtestapplication.connectivity.model.ImageListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AppAPICall {
    @GET("trending")
    fun getImages(@Query("api_key") apiKey:String, @Query("limit") limit:Int, @Query("rating") rating:String): Call<DataResponse>
}