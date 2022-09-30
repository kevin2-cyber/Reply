package io.materialstudies.reply.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.materialstudies.reply.data.Email
import io.materialstudies.reply.data.EmailDiffCallback
import io.materialstudies.reply.databinding.EmailItemLayoutBinding

class EmailAdapter(
    private val listener: EmailAdapterListener
) : ListAdapter<Email, EmailViewHolder>(EmailDiffCallback) {

    interface EmailAdapterListener {
        fun onEmailClicked(cardView: View, email: Email)
        fun onEmailLongPressed(email: Email): Boolean
        fun onEmailStarChanged(email: Email, newValue: Boolean)
        fun onEmailArchived(email: Email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        return EmailViewHolder(
            EmailItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}