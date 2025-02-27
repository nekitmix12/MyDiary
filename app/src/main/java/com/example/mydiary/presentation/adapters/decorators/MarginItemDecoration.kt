package com.example.mydiary.presentation.adapters.decorators

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val marginTop: Int = 0,
    private val marginBottom: Int = 0,
    private val marginLeft: Int = 0,
    private val marginRight: Int = 0,
    private vararg val viewTypes: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (!viewTypes.contains(parent.getChildViewHolder(view).itemViewType)) return

        outRect.set(
            marginLeft.dpToPx(view.context),
            marginTop.dpToPx(view.context),
            marginRight.dpToPx(view.context),
            marginBottom.dpToPx(view.context)
        )

    }

    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }

}