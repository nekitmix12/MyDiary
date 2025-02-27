package com.example.mydiary.presentation.adapters.decorators

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.presentation.models.Gap

class LineItemDecoration(
    private val gap: Gap,
    private vararg val viewTypes: Int,
) : RecyclerView.ItemDecoration() {
    private val dividerPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val alpha = dividerPaint.alpha

    init {
        dividerPaint.color = gap.color
        dividerPaint.strokeWidth = gap.height.toFloat()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (!viewTypes.contains(parent.getChildViewHolder(view).itemViewType)) return

        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        if (position != 0)
            outRect.top = gap.onDivider


        if (position != itemCount - 1)
            outRect.bottom = gap.underDivider


    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount) {
            val view = parent.getChildAt(i)

            val startX = parent.paddingLeft + gap.paddingStart
            val startY = view.bottom + view.translationY + gap.onDivider
            val stopX = parent.width - parent.paddingRight - gap.paddingEnd

            dividerPaint.alpha = (view.alpha * alpha).toInt()

            c.drawLine(startX.toFloat(), startY, stopX.toFloat(), startY, dividerPaint)
        }
    }

}