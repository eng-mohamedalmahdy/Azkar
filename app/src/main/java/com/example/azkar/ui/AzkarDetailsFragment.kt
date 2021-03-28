package com.example.azkar.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.azkar.pojo.AzkarItem
import com.example.azkar.adapters.AzkarListAdapter
import com.example.azkar.R
import com.example.azkar.util.Utils
import com.example.azkar.core.BaseFragment
import com.example.azkar.databinding.FragmentAzkarDetailsBinding
import com.example.azkar.repositories.AzkarNamesRepository

class AzkarDetailsFragment :
    BaseFragment<FragmentAzkarDetailsBinding>(R.layout.fragment_azkar_details) {
    private lateinit var azkarTitlesList: RecyclerView
    private lateinit var search: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAzkarDetailsBinding.bind(view)
        search = binding.search
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val adapter: AzkarListAdapter = azkarTitlesList.adapter as AzkarListAdapter
                adapter.filter(query, requireContext())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


        val azkarList: List<AzkarItem> =
            when (AzkarDetailsFragmentArgs.fromBundle(requireArguments()).AZKARTYPE) {
                R.string.morning_azkar_title -> Utils.getAzkar(requireContext(), "morning", 31)
                R.string.evening_azkar_title -> Utils.getAzkar(requireContext(), "evening", 29)
                R.string.after_prayer_azkar_title -> Utils.getAzkar(
                    requireContext(),
                    "after_prayer",
                    10
                )
                R.string.sleeping_azkar_title -> Utils.getAzkar(
                    requireContext(),
                    "sleeping",
                    11
                )

                else -> AzkarNamesRepository.azkarTitlesList
            }
        azkarTitlesList = binding.azkarDetailsList
        azkarTitlesList.layoutManager = LinearLayoutManager(requireContext())
        azkarTitlesList.adapter =
            AzkarListAdapter(azkarList)
        azkarTitlesList.setHasFixedSize(true)
    }
}