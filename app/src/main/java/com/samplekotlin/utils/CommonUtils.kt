package com.samplekotlin.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.samplekotlin.R

/**
 * Created by Ocs pl-79(17.2.2016) on 1/3/2018.
 */
object CommonUtils {
     fun dialogProgressBarDeclaration(mActivity: Activity): Dialog {
        val customProgressBar = Dialog(mActivity)
        customProgressBar.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customProgressBar.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customProgressBar.setContentView(R.layout.custom_progress_bar)
        customProgressBar.setCancelable(false)
        return customProgressBar
    }
}