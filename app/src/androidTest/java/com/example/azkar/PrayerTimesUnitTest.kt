package com.example.azkar

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.azkar.util.Utils
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PrayerTimesUnitTest {

    @Test
    fun testPrayerFor14Feb2021() {
        val actual = Utils.getPrayerTimes(Calendar.getInstance(), 30.0, 30.0)
        val expected = arrayListOf(
            "05:08",
            "06:35",
            "12:09",
            "15:18",
            "17:42",
            "17:42",
            "19:01"
        )
        Assert.assertEquals(expected, actual)
    }
    
}