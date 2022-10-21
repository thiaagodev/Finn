package com.thiaagodev.finn.view.listener

import com.thiaagodev.finn.service.model.Account

interface OnAccountListener {
    fun onClick(account: Account)

    fun onDelete(account: Account)
}