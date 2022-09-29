package io.materialstudies.reply.ui.nav

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.materialstudies.reply.databinding.NavDividerItemLayoutBinding
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
        private val binding: NavDividerItemLayoutBinding,
        private val listener: NavigationAdapter.NavigationAdapterListener
    ) : NavigationViewHolder<NavigationModelItem.NavDivider>(binding.root) {

        override fun bind(navItem: NavigationModelItem.NavDivider) {
            binding.run {
                navEmailFolder = navItem
                navListener = listener
                executePendingBindings()
            }
        }
    }
}
