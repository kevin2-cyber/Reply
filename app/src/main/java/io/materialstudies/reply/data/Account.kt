package io.materialstudies.reply.data

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import io.materialstudies.reply.R

data class Account(
    val id: Long,
    val uid: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val altEmail: String,
    @DrawableRes val avatar: Int,
    var isCurrentAccount: Boolean = false
) {
    val fullName: String = "$firstName $lastName"
    @DrawableRes val checkedIcon: Int = if (isCurrentAccount) R.drawable.ic_done else 0
}

object AccountDiffCallback : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.email == newItem.email
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.firstName == newItem.firstName
                && oldItem.lastName == newItem.lastName
                && oldItem.avatar == newItem.avatar
                && oldItem.isCurrentAccount == newItem.isCurrentAccount
    }

}
