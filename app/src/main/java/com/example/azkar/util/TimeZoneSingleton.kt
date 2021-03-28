package com.example.azkar.util

import us.dustinj.timezonemap.TimeZone
import us.dustinj.timezonemap.TimeZoneMap
import java.util.*

object TimeZoneSingleton {
    private val map =
        TimeZoneMap.forRegion(-90.0, -180.0, 90.0, 180.0);


    fun getTimeZoneByLatLng(lat: Double, lng: Double): TimeZone? =
        map.getOverlappingTimeZone(lat, lng)
}