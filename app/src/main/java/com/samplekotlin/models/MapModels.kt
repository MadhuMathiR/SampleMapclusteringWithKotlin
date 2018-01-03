package com.samplekotlin.models

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 * Created by Ocs pl-79(17.2.2016) on 1/3/2018.
 */
class MapModels : ClusterItem {
    private var title: String="";
    private var category: String="";
    private var rating: String="";
    private var opennow: String="";
    private var address: String="";
    private var imageURl: String="";
    private var latitude: Double = 0.toDouble()
    private var longitude: Double = 0.toDouble()
    private lateinit var mPosition: LatLng
    fun setTitle(title: String) {
        this.title = title
    }

    fun getCategory(): String {
        return category
    }

    fun setCategory(category: String) {
        this.category = category
    }

    fun getRating(): String {
        return rating
    }

    fun setRating(rating: String) {
        this.rating = rating
    }

    fun getOpennow(): String {
        return opennow
    }

    fun setOpennow(opennow: String) {
        this.opennow = opennow
    }

    fun getAddress(): String {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun getLatitude(): Double {
        return latitude
    }

    fun setLatitude(latitude: Double) {
        this.latitude = latitude
    }

    fun getLongitude(): Double {
        return longitude
    }

    fun setLongitude(longitude: Double) {
        this.longitude = longitude
    }

    fun getImageURl(): String {
        return imageURl
    }

    fun setImageURl(imageURl: String) {
        this.imageURl = imageURl
    }

    override fun getPosition(): LatLng {
        mPosition =LatLng(latitude, longitude)
        return mPosition
    }

    override fun getTitle(): String{
        return title
    }

    override fun getSnippet(): String {
        return address
    }

}