package com.example.azkar.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.azkar.adapters.AzkarListAdapter
import com.example.azkar.R
import com.example.azkar.core.BaseFragment
import com.example.azkar.databinding.FragmentAzkarListBinding
import com.example.azkar.repositories.AzkarNamesRepository

class AzkarListFragment : BaseFragment<FragmentAzkarListBinding>(R.layout.fragment_azkar_list) {

    private lateinit var azkarTitlesList: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAzkarListBinding.bind(view)
        azkarTitlesList = binding.azkarTitlesList
        azkarTitlesList.layoutManager = LinearLayoutManager(context)
        azkarTitlesList.adapter =
            AzkarListAdapter(AzkarNamesRepository.azkarTitlesList)
    }


}