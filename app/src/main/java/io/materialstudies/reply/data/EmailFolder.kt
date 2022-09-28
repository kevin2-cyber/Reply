package io.materialstudies.reply.data

import androidx.recyclerview.widget.DiffUtil


typealias EmailFolder = String
object EmailFolderDiff : DiffUtil.ItemCallback<EmailFolder>() {
    override fun areItemsTheSame(oldItem: EmailFolder, newItem: EmailFolder): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: EmailFolder, newItem: EmailFolder): Boolean {
        return oldItem == newItem
    }
}