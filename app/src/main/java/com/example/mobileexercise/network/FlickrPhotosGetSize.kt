package com.example.mobileexercise.network

import com.example.mobileexercise.models.SizesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrPhotosGetSize {

    @GET("services/rest/")
    fun getImages(
        @Query("method") method : String?,
        @Query("api_key") api_key : String?,
        @Query("photo_id") photo_id : String?,
        @Query("format") format : String?,
        @Query("nojsoncallback") nojsoncallback : Int
    ): Call<SizesResponse>
}