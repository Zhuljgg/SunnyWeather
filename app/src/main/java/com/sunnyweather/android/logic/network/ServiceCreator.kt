package com.sunnyweather.android.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//2.定义好了PlaceService接口，为了能够使用它，还得创建一个Retrofit构建器
//Retrofit构建器
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //提供一个接收Class类型参数的 create()方法
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    //又定义了一个不带参数的create()方法
    //泛型实化，简化代码
    inline fun <reified T> create(): T = create(T::class.java)
}