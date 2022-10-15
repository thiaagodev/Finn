package com.thiaagodev.finn.ui

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thiaagodev.finn.R
import com.thiaagodev.finn.databinding.FragmentHomeBinding
import com.thiaagodev.finn.viewmodel.HomeViewModel
import java.text.SimpleDateFormat

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val hourOfDay =  SimpleDateFormat.getDateTimeInstance().calendar.get(11)
        val welcomeString = if (hourOfDay <= 12) getString(R.string.good_morning) else if(hourOfDay in 13..17) getString(R.string.good_afternoon) else getString(R.string.good_evening)

        binding.textWelcomeMessage.text = welcomeString

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}