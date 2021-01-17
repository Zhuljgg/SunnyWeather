package com.sunnyweather.android.logic.network

import com.sunnyweather.android.LogUtil
import com.sunnyweather.android.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//网络数据源访问入口

//3.还需要再定义一个统一的网络数据源访问入口，对所有网络请求的API进行封装
//  这里用到了
//  suspend关键字，可以将任意函数声明成挂起函数
//  定义一个await()函数，首先其仍然是一个挂起函数，然后给其声明了一个泛型T，并将其定义成了Call<T>的扩展
//      函数，这样所有返回值是Call类型的Retrofit网络请求接口就都可以直接使用await()函数
//  接着，await()函数中使用suspendCoroutine函数来挂起当前协程，并且由于扩展函数的原因，拥有了Call对象的
//      上下文，就可以直接调用enqueue()方法让Retrofit发起网络请求

object SunnyWeatherNetwork {
    //创建一个PlaceService接口的动态代理对象
    private val placeService = ServiceCreator.create<PlaceService>()

    //对WeatherService接口进行封装
    private val weatherService = ServiceCreator.create<WeatherService>()

    //城市搜索
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    //实时天气信息搜索
    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat).await()

    //未来天气信息搜索
    suspend fun getDailyWeather(lng: String, lat: String) =
        weatherService.getDailyWeather(lng, lat).await()

    //
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            //
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()//.body()会返回Callback解析的对象，就是T类型的数据

                    LogUtil.d("SunnyWeatherNetwork", "$body")

                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    "网络请求失败".showToast()
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}