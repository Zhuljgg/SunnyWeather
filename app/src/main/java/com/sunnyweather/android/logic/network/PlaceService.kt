package com.sunnyweather.android.logic.network

import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


//1.首先定义一个用于访问彩云天气城市搜索API的Retrofit接口
interface PlaceService {

    //返回值声明成Call<PlaceResponse>,这样Retrofit会将服务器返回的JSON数据自动解析成PlaceResponse对象
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}