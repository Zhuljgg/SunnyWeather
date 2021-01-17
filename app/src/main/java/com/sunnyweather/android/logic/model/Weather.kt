package com.sunnyweather.android.logic.model

//这个类用于封装Realtime和Daily对象
data class Weather(val realtime: RealtimeResponnse.Realtime, val daily: DailyResponse.Daily)