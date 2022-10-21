package com.thiaagodev.finn.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiaagodev.finn.databinding.RowAccountBinding
import com.thiaagodev.finn.service.model.Account
import com.thiaagodev.finn.view.listener.OnAccountListener
import com.thiaagodev.finn.view.viewholder.AccountViewHolder

class AccountAdapter: RecyclerView.Adapter<AccountViewHolder>() {

    private var accountList: List<Account?> = listOf()
    private lateinit var accountListener: OnAccountListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val item = RowAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AccountViewHolder(item, accountListener)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        accountList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return accountList.count()
    }

    fun updateAccounts(list: List<Account?>) {
        accountList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: OnAccountListener) {
        accountListener = listener
    }

}