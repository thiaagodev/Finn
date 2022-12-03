package com.thiaagodev.finn.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: AccountAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerAccounts.layoutManager = LinearLayoutManager(context)
        binding.recyclerAccounts.adapter = adapter

        binding.imageAddAccount.setOnClickListener(this)

        val accountListener = object : OnAccountListener {
            override fun onClick(account: Account) {
                showAccountFormDialog(account)
            }

            override fun onDelete(account: Account) {
                homeViewModel.deleteAccount(account)
            }

        }

        adapter.attachListener(accountListener)

        observe()

        homeViewModel.getAllAccounts()

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
        homeViewModel.allAccounts.observe(viewLifecycleOwner) {
            it.data?.let { data -> adapter.updateAccounts(data) }
        }

        homeViewModel.saveAccount.observe(viewLifecycleOwner) { resource ->
            resource.getContentIfNotHandled()?.let {
                if (it.error == null) {
                    showSnackBar(getString(R.string.sucess_insert_account))
                } else {
                    showSnackBar(getString(R.string.failure_insert_account))
                }
            }
        }
    }

    private fun showAccountFormDialog(account: Account?) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog).apply {
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }

        val sheetBinding = AccountFormBottomSheetBinding.inflate(layoutInflater, null, false)
        sheetBinding.buttonSaveAccount.setOnClickListener {
            saveAccount(dialog, sheetBinding, account)
        }

        account?.let {
            sheetBinding.editAccountName.setText(it.name)
            sheetBinding.textRegisterAccount.text = getString(R.string.update_account)
        }
        dialog.setContentView(sheetBinding.root)
        dialog.show()

    }

    private fun saveAccount(
        dialog: BottomSheetDialog,
        sheetBinding: AccountFormBottomSheetBinding,
        accountUpdate: Account?
    ) {
        var account = Account()

        accountUpdate?.let {
            account = accountUpdate
        }

        account.name = sheetBinding.editAccountName.text.toString()

        dialog.dismiss()
        homeViewModel.saveAccount(account)
    }


    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}