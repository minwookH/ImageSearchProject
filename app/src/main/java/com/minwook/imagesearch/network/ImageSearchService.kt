package com.minwook.imagesearch.network

import com.minwook.imagesearch.DAO.ImageSearchRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageSearchService {
    @GET("/v2/search/image")
    fun getCurrentWeatherData(@Query("query") query: String,
                              @Query("page") page: Int,
                              @Query("size") size: Int): Call<ImageSearchRes>
}