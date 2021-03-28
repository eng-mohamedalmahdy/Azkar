package com.example.azkar.repositories

import com.example.azkar.R
import com.example.azkar.pojo.AzkarItem

object AzkarNamesRepository {
    val azkarTitlesList =
        listOf(
            AzkarItem(
                0,
                R.string.morning_azkar_title,
                background = R.drawable.morning
            ),
            AzkarItem(
                1,
                R.string.evening_azkar_title,
                background = R.drawable.evening
            ),
            AzkarItem(
                2,
                R.string.sleeping_azkar_title,
                background = R.drawable.sleeping
            ),
            AzkarItem(
                3,
                R.string.after_prayer_azkar_title,
                background = R.drawable.after_prayer
            )
        )
}