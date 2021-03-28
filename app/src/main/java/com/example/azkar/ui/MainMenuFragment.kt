package com.example.azkar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.azkar.R
import com.example.azkar.core.BaseFragment
import com.example.azkar.databinding.FragmentMainMenuBinding


class MainMenuFragment : BaseFragment<FragmentMainMenuBinding>(R.layout.fragment_main_menu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainMenuBinding.bind(view)
        binding.azkarCard.setOnClickListener {
            findNavController().navigate(
                MainMenuFragmentDirections.actionMainMenuFragmentToAzkarListFragment()
            )
        }
    }
}