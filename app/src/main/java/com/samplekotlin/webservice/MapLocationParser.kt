package com.samplekotlin.webservice

import com.samplekotlin.models.MapModels
import org.json.JSONArray
import org.json.JSONException

/**
 * Created by Ocs pl-79(17.2.2016) on 1/3/2018.
 */

    fun parseLocationListData(jsonArray: JSONArray): ArrayList<MapModels> {
        val mapModelsArrayList: ArrayList<MapModels> =ArrayList()
        try {
            for (i in 0 until jsonArray.length()) {
                val mapModels = MapModels()
                if (jsonArray.getJSONObject(i).has("name")) {
                    mapModels.title = jsonArray.getJSONObject(i).optString("name")
                    mapModels.setRating(jsonArray.getJSONObject(i).optString("rating", " "))
                    if (jsonArray.getJSONObject(i).has("opening_hours")) {
                        if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").has("open_now")) {
                            if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").getString("open_now") == "true") {
                                mapModels.setOpennow("YES")
                            } else {
                                mapModels.setOpennow("NO")
                            }
                        }
                    } else {
                        mapModels.setOpennow("Not Known")
                    }
                    if (jsonArray.getJSONObject(i).has("geometry")) {
                        if (jsonArray.getJSONObject(i).getJSONObject("geometry").has("location")) {

                            mapModels.setLatitude(java.lang.Double.parseDouble(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lat")))
                            mapModels.setLongitude(java.lang.Double.parseDouble(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lng")))
                        }
                    }
                    if (jsonArray.getJSONObject(i).has("vicinity")) {
                        mapModels.setAddress(jsonArray.getJSONObject(i).optString("vicinity"))
                    }
                    if (jsonArray.getJSONObject(i).has("icon")) {
                        mapModels.setImageURl(jsonArray.getJSONObject(i).optString("icon"))
                    }
                    if (jsonArray.getJSONObject(i).has("types")) {
                        val typesArray = jsonArray.getJSONObject(i).getJSONArray("types")
                        for (j in 0 until typesArray.length()) {
                            mapModels.setCategory(typesArray.getString(j) + ", " + mapModels.getCategory())
                        }
                    }
                }
                mapModelsArrayList.add(mapModels)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return mapModelsArrayList
    }

