package io.materialstudies.reply.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.materialstudies.reply.R
import io.materialstudies.reply.data.Email
import io.materialstudies.reply.databinding.EmailItemLayoutBinding
import io.materialstudies.reply.util.themeStyle
import com.google.android.material.R.attr.textAppearanceHeadline4
import com.google.android.material.R.attr.textAppearanceHeadline5
import io.materialstudies.reply.util.setTextAppearanceCompat
import kotlin.math.abs

class EmailViewHolder(
    private val binding: EmailItemLayoutBinding,
    listener: EmailAdapter.EmailAdapterListener
) : RecyclerView.ViewHolder(binding.root), ReboundingSwipeActionCallback.ReboundableViewHolder{
    private val attachmentAdapter = object : EmailAttachmentAdapter() {
        override fun getLayoutIdForPositiion(position: Int): Int{
            return R.layout.email_attachment_preview_item_layout
        }
    }

    private val starredCornerSize =
        itemView.resources.getDimension(R.dimen.reply_small_component_corner_radius)

    override val reboundableView: View = binding.cardView

    init {
        binding.run {
            this.listener = listener
            attachmentRecyclerView.adapter = attachmentAdapter
            root.background = EmailSwipeActionDrawable(root.context)
        }
    }

    fun bind(email: Email) {
        binding.email = email
        binding.root.isActivated = email.isStarred

        // Set the subject's TextAppearance
        val textAppearance = binding.subjectTextView.context.themeStyle(
            if (email.isImportant) {
                textAppearanceHeadline4
            } else {
                textAppearanceHeadline5
            }
        )
        binding.subjectTextView.setTextAppearanceCompat(
            binding.subjectTextView.context,
            textAppearance
        )

        attachmentAdapter.submitList(email.attachments)

        // Setting interpolation here controls whether or not we draw the top left corner as
        // rounded or squared. Since all other corners are set to 0dp rounded, they are
        // not affected.
        val interpolation = if (email.isStarred) 1F else 0F
        updateCardViewTopLeftCornerSize(interpolation)

        binding.executePendingBindings()
    }

    override fun onReboundOffSetChanged(
        currentSwipePercentage: Float,
        swipeThreshold: Float,
        currentTargetHasMetThresholdOnce: Boolean
    ) {
        // Only alter shape and activation in the forward direction once the swipe
        // threshold has been met. Undoing the swipe would require releasing the item and
        // re-initiating the swipe.
        if (currentTargetHasMetThresholdOnce) return

        val isStarred = binding.email?.isStarred ?: false

        // Animate the top left corner radius of the email card as swipe happens.
        val interpolation = (currentSwipePercentage / swipeThreshold).coerceIn(0F, 1F)
        val adjustedInterpolation = abs(
            (if (isStarred) 1F else 0F) - interpolation
        )
        updateCardViewTopLeftCornerSize(adjustedInterpolation)

        // Start the background animation once the threshold is met.
        val thresholdMet = currentSwipePercentage >= swipeThreshold
        val shouldStar = when {
            thresholdMet && isStarred -> false
            thresholdMet && !isStarred -> true
            else -> return
        }
        binding.root.isActivated = shouldStar
    }

    override fun onRebounded() {
        val email = binding.email ?: return
        binding.listener?.onEmailStarChanged(email, !email.isStarred)
    }

    // We have to update the shape appearance itself to have the MaterialContainerTransform pick up
    // the correct shape appearance, since it doesn't have access to the MaterialShapeDrawable
    // interpolation. If you don't need this work around, prefer using MaterialShapeDrawable's
    // interpolation property, or in the case of MaterialCardView, the progress property.
    private fun updateCardViewTopLeftCornerSize(interpolation: Float) {
        binding.cardView.apply {
            shapeAppearanceModel = shapeAppearanceModel.toBuilder()
                .setTopLeftCornerSize(interpolation * starredCornerSize)
                .build()
        }
    }
}