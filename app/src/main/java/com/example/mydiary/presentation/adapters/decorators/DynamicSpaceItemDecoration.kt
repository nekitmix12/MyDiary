package com.example.mydiary.presentation.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DynamicSpaceItemDecoration(

    private vararg val viewTypes: Int,
) : RecyclerView.ItemDecoration() {

    private var recyclerViewHeight = 0


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (!viewTypes.contains(parent.getChildViewHolder(view).itemViewType)) return

        if (recyclerViewHeight == 0) {
            recyclerViewHeight = parent.height
        }
        if (recyclerViewHeight > 0) {
            val targetHeight = (recyclerViewHeight * 0.9).toInt()
            val itemHeight = view.layoutParams.height

            if (itemHeight < targetHeight) {
                outRect.bottom = targetHeight - itemHeight
            }
        }

    }
}