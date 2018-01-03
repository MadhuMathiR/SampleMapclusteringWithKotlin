package com.samplekotlin.webservice

import android.content.Context
import android.util.Log
import com.samplekotlin.R
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.concurrent.TimeUnit

/**
 * Created by Ocs pl-79(17.2.2016) on 1/3/2018.
 */
class MapWebservice(var webServiceListener: WebServiceListener, var context: Context) {

    fun getLocationList() {
        try {
            val URL = WebServiceApi.LOCATION_API + context.getString(R.string.google_maps_key)

            val okHttpClient = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
            val request = Request.Builder().url(URL).build()
            val response = okHttpClient.newCall(request).execute()
            val responseBody = response.body()
            Log.d("Meyda", URL)
            if (response.isSuccessful) {
                val jsonObjectResponse = JSONObject(responseBody!!.string())
                val StringResponse = jsonObjectResponse.toString()
                Log.d("Stringresponse", StringResponse)


                val jsonArray = jsonObjectResponse.optJSONArray("results")
                val mapModelsArrayList = parseLocationListData(jsonArray)

                webServiceListener.onSuccess(context.getString(R.string.success), "", mapModelsArrayList)
            } else {
                webServiceListener.onError(context.getString(R.string.error), "")
            }


        } catch (e: Exception) {
            e.printStackTrace()

        }

    }
}