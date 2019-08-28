package com.minwook.imagesearch.model

import android.content.Context
import android.util.Log
import com.minwook.imagesearch.DAO.ImageSearchRes
import com.minwook.imagesearch.model.NetworkCallback
import com.minwook.imagesearch.network.ImageSearchService
import com.minwook.imagesearch.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListModel(var networkCallback: NetworkCallback) {

    fun dataLoad(query: String, page: Int, size: Int) {
        Log.e("test", "dataLoad start")

        val restClient: ImageSearchService =
            NetworkManager.getRetrofitService(ImageSearchService::class.java)

        /*
         * 현재 날씨 정보를 비동기 방식으로 요청
         * Retrofit2을 사용하면 별도의 Thread(AsyncTask)를 만들필요없이
         * 비동기 방식으로 동작하도록 구성할 수 있다
         */
        val currentWeather = restClient.getCurrentWeatherData(query, page, size)

        currentWeather.enqueue(object : Callback<ImageSearchRes>{
            override fun onFailure(call: Call<ImageSearchRes>, t: Throwable) {

                Log.e("test", "onFailure start")
                Log.e("test", t.localizedMessage)
                networkCallback.getResponse(null, 400)
            }

            override fun onResponse(call: Call<ImageSearchRes>, response: Response<ImageSearchRes>) {
                Log.d("test", "onResponse start")

                val imageSearchRes = response.body()
                Log.d("test", "${imageSearchRes?.meta}")
                Log.d("test", "${imageSearchRes?.documents?.size}")

                if (imageSearchRes?.meta?.total_count == 0){
                    networkCallback.getResponse(imageSearchRes.documents, 201)
                }else{
                    networkCallback.getResponse(imageSearchRes?.documents, 200)
                }

            }
        })
    }
}