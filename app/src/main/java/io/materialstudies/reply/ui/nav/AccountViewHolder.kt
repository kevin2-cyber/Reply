package io.materialstudies.reply.ui.nav

import androidx.recyclerview.widget.RecyclerView
import io.materialstudies.reply.data.Account
import io.materialstudies.reply.databinding.AccountItemLayoutBinding

class AccountViewHolder(
    val binding: AccountItemLayoutBinding,
    val listener: AccountAdapter.AccountAdapterListener
) : RecyclerView.ViewHolder(binding.root){

    fun bind(accnt: Account) {
        binding.run {
            account = accnt
            accountListener = listener
            executePendingBindings()
        }
    }
}