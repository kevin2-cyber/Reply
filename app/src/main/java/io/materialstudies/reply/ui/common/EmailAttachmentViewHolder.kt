package io.materialstudies.reply.ui.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.materialstudies.reply.BR
import io.materialstudies.reply.data.EmailAttachment

/**
 * Generic RecyclerView.ViewHolder which is able to bind layouts which expose a variable
 * for an [EmailAttachment].
 */
class EmailAttachmentViewHolder(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(attachment: EmailAttachment) {
        binding.run {
            setVariable(BR.emailAttachment, attachment)
            executePendingBindings()
        }
    }
}