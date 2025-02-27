package com.example.mydiary.presentation.adapters.holders

import android.widget.ImageView
import android.widget.LinearLayout
import com.example.mydiary.databinding.EmotionLogsBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.EmotionByDayModel

class EmotionByDayHolder(
    binding: EmotionLogsBinding,
) :
    BaseViewHolder<EmotionLogsBinding, EmotionByDayModel>(binding) {
    override fun onBinding(item: EmotionByDayModel) = with(binding) {
        emotionData.text = item.data
        emotionType.text = item.emotionType
        emotionFlexLayout.removeAllViews()
        for (i in item.listDrawable.indices) {
            val imageView = ImageView(binding.root.context).apply {
                setImageDrawable(item.listDrawable[i])
                layoutParams = LinearLayout.LayoutParams(
                    item.imageSize,
                    item.imageSize
                )
            }
            emotionFlexLayout.addView(imageView)
        }
    }
}