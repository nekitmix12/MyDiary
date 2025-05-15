package com.example.mydiary.presentation.adapters.holders

import com.example.mydiary.databinding.LogbookCycrleButtonBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.CircleButton

class LogbookCircleButtonHolder(
    binding: LogbookCycrleButtonBinding,
    private val onClick: () -> Unit,
) :
    BaseViewHolder<LogbookCycrleButtonBinding, CircleButton>(binding) {
    override fun onBinding(item: CircleButton) = with(binding) {
        progressBarView.stroke = item.stroke
        progressBarView.rotate = item.rotate
        imageButton.setOnClickListener {
            onClick()
        }
    }
}