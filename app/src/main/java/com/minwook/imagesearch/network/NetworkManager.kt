package com.minwook.imagesearch.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object NetworkManager {

    private val ALL_TIMEOUT = 10L
    private val API_KEY = "a46ff4d3ff422ded28c5aff151b130a2"
    private val HOST = "https://dapi.kakao.com/"

    private var okHttpClient: OkHttpClient
    private var retrofit: Retrofit

    init{
        /*
         * 로깅 인터셉터 연결
         */
        Log.e("test", "NetworkManager init")
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BASIC


        okHttpClient = OkHttpClient().newBuilder().apply {

            addInterceptor(httpLogging)
            addInterceptor(HeaderSettingInterceptor())
            connectTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)

        }.build()
        /*
         * Retrofit2 + OKHttp3를 연결한다
         */
        retrofit = Retrofit.Builder().apply{
            baseUrl(HOST)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }
    /*
     *  Request Header를 세팅하는 Interceptor
     */
    private  class HeaderSettingInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            Log.e("test", "HeaderSettingInterceptor intercept")

            val chainRequest = chain.request()

            val request = chainRequest.newBuilder().apply{
                addHeader("Authorization", "KakaoAK "+API_KEY)
            }.build()

            return chain.proceed(request)
        }
    }
    /*
     * 현재 선언된 요청 인터페이스 객체(RetrofitInterface)를 리턴한다
     */
    internal fun <T> getRetrofitService(restClass: Class<T>): T {
        Log.e("test", "getRetrofitService start")
        return retrofit.create(restClass)
    }
}