package com.example.azkar.util

import android.content.Context
import android.graphics.Color
import com.example.azkar.pojo.AzkarItem
import java.util.*
import kotlin.collections.ArrayList

object Utils {
    fun getComplementaryColor(colorToInvert: Int): Int {
        val hsv = FloatArray(3)
        Color.RGBToHSV(
            Color.red(colorToInvert), Color.green(colorToInvert),
            Color.blue(colorToInvert), hsv
        )
        hsv[0] = (hsv[0] + 180) % 360
        return Color.HSVToColor(hsv)
    }


    fun getAzkar(context: Context, azkarName: String, azkarCount: Int): List<AzkarItem> {
        val list = mutableListOf<AzkarItem>()

        for (i in 1..azkarCount) {
            val contentId: Int =
                context.resources.getIdentifier(
                    "${azkarName}_azkar_$i",
                    "string",
                    context.packageName
                )

            val drawableId = context.resources.getIdentifier(
                azkarName,
                "drawable",
                context.packageName
            )
            list.add(
                AzkarItem(
                    i.toLong(),
                    content = contentId,
                    background = drawableId
                )
            )
        }
        return list.toList()
    }

    fun getPrayerTimes(calendar: Calendar, lat: Double, lng: Double): ArrayList<String> {
        val timeZone = TimeZoneSingleton.getTimeZoneByLatLng(lat, lng)
        val timeZoneOffset = TimeZone.getTimeZone(timeZone!!.zoneId).rawOffset / 3600000.0
        val prayerTimes = PrayerTimeSingleton.getPrayTime(calendar, lat, lng, timeZoneOffset)
        return prayerTimes
    }
}