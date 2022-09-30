package io.materialstudies.reply.ui.nav

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.materialstudies.reply.databinding.NavDividerItemLayoutBinding
import io.materialstudies.reply.databinding.NavEmailFolderItemLayoutBinding
import io.materialstudies.reply.databinding.NavMenuItemLayoutBinding

sealed class NavigationViewHolder<T: NavigationModelItem>(
    view: View
) : RecyclerView.ViewHolder(view) {
    abstract fun bind(navItem: T)

    class NavMenuItemViewHolder(
        private val binding: NavMenuItemLayoutBinding,
        private val listener: NavigationAdapter.NavigationAdapterListener
    ) : NavigationViewHolder<NavigationModelItem.NavMenuItem>(binding.root) {

        override fun bind(navItem: NavigationModelItem.NavMenuItem) {
            binding.run {
                navMenuItem = navItem
                navListener = listener
                executePendingBindings()
            }
        }
    }

    class NavDividerViewHolder(
        private val binding: NavEmailFolderItemLayoutBinding,
        private val listener: NavigationAdapter.NavigationAdapterListener
    ) : NavigationViewHolder<NavigationModelItem.NavEmailFolder>(binding.root) {

        override fun bind(navItem: NavigationModelItem.NavEmailFolder) {
            binding.run {
                navEmailFolder = navItem
                navListener = listener
                executePendingBindings()
            }
        }
    }
}
