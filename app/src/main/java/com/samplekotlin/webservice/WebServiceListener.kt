package com.samplekotlin.webservice

/**
 * Created by Ocs pl-79(17.2.2016) on 1/3/2018.
 */
interface WebServiceListener {

    fun onError(message: String, response: String)

    fun onSuccess(message: String, response: String, `object`: Any)

}