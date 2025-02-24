package com.example.mydiary.presentation.adapters.holders

import com.example.mydiary.databinding.LogbookLabelBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.LabelModel

class LabelHolder(
    binding: LogbookLabelBinding,
) :
    BaseViewHolder<LogbookLabelBinding, LabelModel>(binding) {
    override fun onBinding(item: LabelModel) = with(binding) {
        labelText.text = item.text
    }
}