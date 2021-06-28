package com.example.mobileexercise.network

import com.example.mobileexercise.models.PhotoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrPhotosSearch {

    @GET("services/rest/")
    fun getSearch(
        @Query("method") method : String?,
        @Query("api_key") api_key : String?,
        @Query("tags") tags : String?,
        @Query("page") page : Int,
        @Query("format") format : String?,
        @Query("nojsoncallback") nojsoncallback : Int
    ): Call<PhotoResponse>
}