package com.example.azkar.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.azkar.R
import com.example.azkar.core.BaseFragment
import com.example.azkar.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.alpha = 0f
        view.animate().setDuration(2000).alpha(1f)
            .withEndAction {
                findNavController().navigate(R.id.action_splashFragment_to_mainListFragment)
            }.start()

    }
}