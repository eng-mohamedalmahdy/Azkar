package com.example.azkar

import android.content.Context
import android.graphics.Color

class Utils {
    companion object {
        fun getComplementaryColor(colorToInvert: Int): Int {
            val hsv = FloatArray(3)
            Color.RGBToHSV(
                Color.red(colorToInvert), Color.green(colorToInvert),
                Color.blue(colorToInvert), hsv
            )
            hsv[0] = (hsv[0] + 180) % 360
            return Color.HSVToColor(hsv)
        }


        val azkarTitlesList =
            listOf(
                AzkarItem(0, R.string.morning_azkar_title, background = R.drawable.morning),
                AzkarItem(1, R.string.evening_azkar_title, background = R.drawable.evening),
                AzkarItem(2, R.string.sleeping_azkar_title, background = R.drawable.sleeping),
                AzkarItem(
                    3,
                    R.string.after_prayer_azkar_title,
                    background = R.drawable.after_prayer
                )
            )

        fun getAzkar(context: Context, azkarName: String, azkarCount: Int): List<AzkarItem> {
            val list = mutableListOf<AzkarItem>()
            for (i in 1..azkarCount) {
                val contentId: Int =
                    context.resources.getIdentifier(
                        "${azkarName}_azkar_$i",
                        "string",
                        context.packageName
                    )
                val drawableId =context.resources.getIdentifier(
                    azkarName,
                    "drawable",
                    context.packageName
                )
                list.add(AzkarItem(i.toLong(), content = contentId, background = drawableId))
            }
            return list.toList()
        }
    }
}