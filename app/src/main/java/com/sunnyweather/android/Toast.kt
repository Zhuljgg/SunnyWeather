package com.sunnyweather.android

import android.widget.Toast

//自定义拓展函数，简化toast用法
fun String.showToast(duration:Int=Toast.LENGTH_SHORT){
    Toast.makeText(SunnyWeatherApplication.context,this,duration).show()
}

fun Int.showToast(duration:Int=Toast.LENGTH_SHORT){
    Toast.makeText(SunnyWeatherApplication.context,this,duration).show()
}