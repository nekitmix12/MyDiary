package com.example.mydiary.presentation.adapters.holders

import android.widget.LinearLayout
import com.example.mydiary.databinding.EmotionIndicatorBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.EmotionIndicatorModel

class EmotionIndicatorHolder(
    binding: EmotionIndicatorBinding,
) :
    BaseViewHolder<EmotionIndicatorBinding, EmotionIndicatorModel>(binding) {
    override fun onBinding(item: EmotionIndicatorModel) = with(binding) {
        indicatorIcon.setImageDrawable(item.icon)
        emotionIndicatorName.text = item.emotion
        emotionPercent.background = item.background
        emotionPercent.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            0.468f * item.partOf
        )
        spaceEmotionPercent.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            0.468f * (1 - item.partOf)
        )
        emotionPercent.text = item.count
    }
}