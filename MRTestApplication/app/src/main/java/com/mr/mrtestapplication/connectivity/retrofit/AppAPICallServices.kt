package com.roro.roroassignment.connectivity.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppAPICallServices {

    private val BASE_URL = "https://api.giphy.com/v1/gifs/"

    private val api =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppAPICall::class.java)

    fun imageApiCall(api_key:String,limit:Int,rating:String) = api.getImages(api_key,limit,rating)
}