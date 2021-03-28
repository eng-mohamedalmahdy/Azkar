package com.example.azkar.util

import java.util.*

object PrayerTimeSingleton {
    private val prayerTimes = PrayTime()

    init {
        prayerTimes.calcMethod = PrayTime.Egypt
        prayerTimes.asrJuristic = PrayTime.Hanafi
        prayerTimes.adjustHighLats = PrayTime.None
        prayerTimes.timeFormat = PrayTime.Time24
        val offsets =
            intArrayOf(-5, -6, -5, -52, -6, -6, -5) // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
        prayerTimes.tune(offsets)
    }

    fun getPrayTime(date: Calendar, lat: Double, lng: Double, timeZone: Double): ArrayList<String> =
        prayerTimes.getPrayerTimes(date, lat, lng, timeZone)
}