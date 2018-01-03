package com.samplekotlin

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.samplekotlin.models.MapModels
import com.samplekotlin.utils.CommonUtils
import com.samplekotlin.utils.DefaultClusterRenderer
import com.samplekotlin.utils.InternetisConnected
import com.samplekotlin.webservice.MapWebservice
import com.samplekotlin.webservice.WebServiceListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback, WebServiceListener {


    private val mActivity: Activity = this
    private val mContext: Context = this
    private lateinit var mMap: GoogleMap
    var dialog: Dialog? = null
    var webservice: MapWebservice? = null
    var clusterManager: ClusterManager<MapModels>? = null
    internal var mapModelsArrayList = ArrayList<MapModels>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.setTitle(R.string.title_activity_maps)
        setSupportActionBar(toolbar)
        dialog = CommonUtils.dialogProgressBarDeclaration(mActivity)
        webservice = MapWebservice(this, mContext)
        initializeMap()
        checkInternetAndLaunchLocationListApi()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        initializeMap()
    }

    fun initializeMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    fun checkInternetAndLaunchLocationListApi() {
        if (InternetisConnected(mActivity)) {
            launchLocationListApi()
        } else {

            Toast.makeText(mContext, "No internet connection", Toast.LENGTH_SHORT).show()

        }

    }

    fun launchLocationListApi() {


        val thread = object : Thread() {
            override fun run() {
                showProgressBar()
                webservice?.getLocationList()


            }
        }
        thread.start()
    }

    fun showProgressBar() {
        mActivity.runOnUiThread { dialog?.show() }
    }

    fun hideProgressBar() {
        mActivity.runOnUiThread { dialog?.dismiss() }
    }

    private fun setCluster() {

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(mapModelsArrayList.get(0).getLatitude(), mapModelsArrayList.get(0).getLongitude()), 14f))
        clusterManager = ClusterManager(mContext, mMap)
        clusterManager?.setRenderer(DefaultClusterRenderer<MapModels>(mContext, mMap, clusterManager))
        mMap.setOnCameraIdleListener(clusterManager)

        for (i in mapModelsArrayList.indices) {
            val infoWindowItem = MapModels()
            infoWindowItem.setLatitude(mapModelsArrayList.get(i).getLatitude())
            infoWindowItem.setLongitude(mapModelsArrayList.get(i).getLongitude())
            infoWindowItem.title = mapModelsArrayList.get(i).getTitle()
            infoWindowItem.setAddress(mapModelsArrayList.get(i).getAddress())
            clusterManager?.addItem(infoWindowItem)
        }
    }

    override fun onError(message: String, response: String) {
        mActivity?.runOnUiThread {
            println("message")
            hideProgressBar()
        }
    }

    override fun onSuccess(message: String, response: String, `object`: Any) {
        mActivity?.runOnUiThread {
            mapModelsArrayList = `object` as java.util.ArrayList<MapModels>
            setCluster()
            hideProgressBar()
        }
    }

}
