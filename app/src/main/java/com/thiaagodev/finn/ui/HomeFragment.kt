package com.thiaagodev.finn.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.thiaagodev.finn.R
import com.thiaagodev.finn.databinding.AccountFormBottomSheetBinding
import com.thiaagodev.finn.databinding.FragmentHomeBinding
import com.thiaagodev.finn.viewmodel.HomeViewModel
import java.text.SimpleDateFormat

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.imageAddAccount.setOnClickListener(this)

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

    override fun onClick(view: View) {
        when(view.id) {
            R.id.image_add_account -> {
                showAccountFormDialog()
            }
        }
    }

    private fun showAccountFormDialog() {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog).apply {
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
        val sheetBinding = AccountFormBottomSheetBinding.inflate(layoutInflater, null, false)
        dialog.setContentView(sheetBinding.root)
        dialog.show()

    }

}