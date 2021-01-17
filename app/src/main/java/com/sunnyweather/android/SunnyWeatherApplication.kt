package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {
    //提供一种全局获取Context的方式
    companion object {
        //彩云天气开发者平台申请到的令牌值
        const val TOKEN = "kCLqjXSAh6dePvpc"

        @SuppressLint("StaticFieldLeak")//添加注解，让AS忽略警告提示
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}