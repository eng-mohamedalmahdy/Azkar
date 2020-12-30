package com.example.azkar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AzkarDetailsActivity : AppCompatActivity() {
    private lateinit var azkarTitlesList: RecyclerView
    private lateinit var search: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_azkar_details)

        search = findViewById(R.id.search)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val adapter: AzkarListAdapter = azkarTitlesList.adapter as AzkarListAdapter
                adapter.filter(query, this@AzkarDetailsActivity)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        val azkarList: List<AzkarItem> =
            when (intent.getIntExtra("AZKAR_TYPE", R.string.morning_azkar_title)) {
                R.string.morning_azkar_title -> Utils.getAzkar(this, "morning", 31)
                R.string.evening_azkar_title -> Utils.getAzkar(this, "evening", 29)
                R.string.after_prayer_azkar_title -> Utils.getAzkar(
                    this,
                    "after_prayer",
                    10
                )
                R.string.sleeping_azkar_title -> Utils.getAzkar(
                    this,
                    "sleeping",
                    11
                )

                else -> Utils.azkarTitlesList
            }
        azkarTitlesList = findViewById(R.id.azkar_details_list)
        azkarTitlesList.layoutManager = LinearLayoutManager(this)
        azkarTitlesList.adapter = AzkarListAdapter(azkarList)
        azkarTitlesList.setHasFixedSize(true)
    }
}