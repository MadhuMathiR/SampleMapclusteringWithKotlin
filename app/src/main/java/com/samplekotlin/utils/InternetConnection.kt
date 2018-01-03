package com.samplekotlin.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.samplekotlin.R

/**
 * Created by Ocs pl-79(17.2.2016) on 1/3/2018.
 */

fun InternetisConnected(mActivity: Activity): Boolean {
    val cm = mActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = cm.activeNetworkInfo
    val return_status = activeNetwork != null && activeNetwork.isConnectedOrConnecting
    if (!return_status) {
        Toast.makeText(mActivity, mActivity.getString(R.string.alert_no_internet), Toast.LENGTH_SHORT).show()

    }
    return return_status
}

