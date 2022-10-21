package com.thiaagodev.finn.view.viewholder

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.thiaagodev.finn.R
import com.thiaagodev.finn.databinding.RowAccountBinding
import com.thiaagodev.finn.service.model.Account
import com.thiaagodev.finn.view.listener.OnAccountListener
import java.text.NumberFormat
import java.util.*

class AccountViewHolder(
    private val binding: RowAccountBinding,
    private val listener: OnAccountListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(account: Account) {
        binding.textAccountName.text = account.name

        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 2
        numberFormat.currency = Currency.getInstance("BRL")

        binding.textAccountBalance.text = numberFormat.format(account.balance).toString()

        if (account.balance < 0) {
            binding.textAccountBalance.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.red
                )
            )
        }

        binding.root.setOnClickListener {
            listener.onClick(account)
        }

        binding.root.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(itemView.context.getString(R.string.remove_account))
                .setMessage(
                    itemView.context.getString(
                        R.string.remove_account_message,
                        account.name
                    )
                )
                .setPositiveButton(
                    itemView.context.getString(
                        R.string.positive_button
                    )
                ) { _, _ -> listener.onDelete(account) }
                .setNegativeButton(itemView.context.getString(R.string.negative_button), null)
                .create()
                .show()
            true
        }

    }
}