package com.iago.ptest

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.location.Location
import com.google.android.gms.maps.model.LatLng

private const val LATITUDE = "latitude"
private const val LONGITUDE = "longitude"
private const val FILE_NAME = "Spot"

class SpotManager(context: Context) {

    private val sharedPrefs = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)

    fun saveSpot(location: Location) {
        sharedPrefs
            .edit()
            .putString(LATITUDE, location.latitude.toString())
            .putString(LONGITUDE, location.longitude.toString())
            .apply()
    }

    fun getSpot(): LatLng? {
        if (sharedPrefs.contains(LATITUDE) && sharedPrefs.contains(LONGITUDE)) {
            val location = LatLng(
                sharedPrefs.getString(LATITUDE, "0")?.toDouble() ?: 0.0,
                sharedPrefs.getString(LONGITUDE, "0")?.toDouble() ?: 0.0
            )
            if (location.latitude == 0.0 && location.longitude == 0.0) {
                return null
            } else {
                return location
            }
        }
        return null
    }
}