package com.sunnyweather.android.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.LogUtil
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Location

class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()

    //这3个定义的变量，和界面相关的数据，放到ViewModel可以保证它们在手机屏幕发生旋转的时候不会丢失，
    //  稍后，在编写UI层代码的时候会用到这几个变量
    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->

        LogUtil.d("WeatherViewModel","${location.lng},${location.lat}")

        Repository.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)

        LogUtil.d("WeatherViewModel refresh","${lng},${lat}")
    }
}