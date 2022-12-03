package com.thiaagodev.finn.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.thiaagodev.finn.databinding.FragmentCashFlowBinding
import com.thiaagodev.finn.viewmodel.CashFlowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CashFlowFragment : Fragment() {

    private var _binding: FragmentCashFlowBinding? = null
    private val binding get() = _binding!!
    private val cashFlowViewModel: CashFlowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCashFlowBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val textView: TextView = binding.textNotifications
        cashFlowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}