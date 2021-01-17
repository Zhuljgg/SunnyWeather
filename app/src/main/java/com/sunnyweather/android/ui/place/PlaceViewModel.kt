package com.sunnyweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Place

//5，实现逻辑层，最后一步:定义ViewModel层
//  ViewModel相当于逻辑层和UI层之间的一个桥梁，虽然它更偏向于逻辑层的部分，但是由于ViewModel通常
//  和Activity或Fragment是一一对应的，因此我们还是习惯将它们放在一起
class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    //定义一个placeList集合
    val placeList = ArrayList<Place>()

    //在转换函数中，只需要调用仓库层中定义的searchPlaces()方法就可以发起网络请求
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    //首先定义一个方法,一旦searchLiveData的数据发生变化，那么观察它的switchMap()方法就会执行，
    //  并且调用我们编写的转换函数，然后在转换函数中调用方法获取真正的数据
    //  同时switchMap()方法会将返回的LiveData对象转换成一个可观察的LiveData对象，
    //  对于Activity而言，只要去观察这个LiveData对象就可以了
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavedPlace() = Repository.getSavedPlace()
    fun isPlaceSaved() = Repository.isPlaceSaved()
}