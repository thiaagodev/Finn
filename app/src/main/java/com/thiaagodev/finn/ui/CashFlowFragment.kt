package com.thiaagodev.finn.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thiaagodev.finn.databinding.FragmentCashFlowBinding
import com.thiaagodev.finn.viewmodel.CashFlowViewModel

class CashFlowFragment : Fragment() {

    private var _binding: FragmentCashFlowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cashFlowViewModel =
            ViewModelProvider(this).get(CashFlowViewModel::class.java)

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