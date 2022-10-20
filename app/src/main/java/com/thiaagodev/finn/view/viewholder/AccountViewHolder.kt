package com.thiaagodev.finn.view.viewholder

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.thiaagodev.finn.R
import com.thiaagodev.finn.databinding.RowAccountBinding
import com.thiaagodev.finn.service.model.Account
import java.text.NumberFormat
import java.util.*

class AccountViewHolder(private val binding: RowAccountBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(account: Account) {
        binding.textAccountName.text = account.name

        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 2
        numberFormat.currency = Currency.getInstance("BRL")

        binding.textAccountBalance.text = numberFormat.format(account.balance).toString()

        if (account.balance < 0) {
            binding.textAccountBalance.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
    }
}