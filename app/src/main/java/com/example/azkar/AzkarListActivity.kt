package com.example.azkar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AzkarListActivity : AppCompatActivity() {
    private lateinit var azkarTitlesList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_azkar_list)
        azkarTitlesList = findViewById(R.id.azkar_titles_list)
        azkarTitlesList.layoutManager = LinearLayoutManager(this)
        azkarTitlesList.adapter = AzkarListAdapter(Utils.azkarTitlesList)
    }
}