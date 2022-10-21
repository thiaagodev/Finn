package com.thiaagodev.finn.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.thiaagodev.finn.R
import com.thiaagodev.finn.databinding.AccountFormBottomSheetBinding
import com.thiaagodev.finn.databinding.FragmentHomeBinding
import com.thiaagodev.finn.service.model.Account
import com.thiaagodev.finn.view.adapter.AccountAdapter
import com.thiaagodev.finn.view.listener.OnAccountListener
import com.thiaagodev.finn.viewmodel.HomeViewModel
import java.text.SimpleDateFormat

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private val adapter = AccountAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerAccounts.layoutManager = LinearLayoutManager(context)
        binding.recyclerAccounts.adapter = adapter

        binding.imageAddAccount.setOnClickListener(this)

        val accountListener = object: OnAccountListener {
            override fun onClick(account: Account) {
                showAccountFormDialog(account)
            }

            override fun onDelete(account: Account) {
                homeViewModel.deleteAccount(account)
            }

        }

        adapter.attachListener(accountListener)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val hourOfDay = SimpleDateFormat.getDateTimeInstance().calendar.get(11)
        val welcomeString =
            if (hourOfDay <= 12) getString(R.string.good_morning) else if (hourOfDay in 13..17) getString(
                R.string.good_afternoon
            ) else getString(R.string.good_evening)

        binding.textWelcomeMessage.text = welcomeString


        homeViewModel.getAllAccounts()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.image_add_account -> {
                showAccountFormDialog(null)
            }
        }
    }

    private fun observe() {
        homeViewModel.accounts.observe(viewLifecycleOwner) {
            adapter.updateAccounts(it)
        }

        homeViewModel.saveAccount.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { msg ->
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
            }

        }
    }

    private fun showAccountFormDialog(account: Account?) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog).apply {
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }

        val sheetBinding = AccountFormBottomSheetBinding.inflate(layoutInflater, null, false)
        sheetBinding.buttonSaveAccount.setOnClickListener {
            saveAccount(dialog, sheetBinding, account?.id)
        }

        account?.let {
            sheetBinding.editAccountName.setText(it.name)
        }

        dialog.setContentView(sheetBinding.root)
        dialog.show()

    }

    private fun saveAccount(
        dialog: BottomSheetDialog,
        sheetBinding: AccountFormBottomSheetBinding,
        id: Long?
    ) {
        val account = Account()
        account.name = sheetBinding.editAccountName.text.toString()
        account.id = id ?: 0

        dialog.dismiss()
        homeViewModel.saveAccount(account)
    }

}