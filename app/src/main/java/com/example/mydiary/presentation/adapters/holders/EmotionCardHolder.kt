package com.example.mydiary.presentation.adapters.holders

import android.annotation.SuppressLint
import com.example.mydiary.databinding.LogbookEmotionCardBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.EmotionCardModel

class EmotionCardHolder(binding: LogbookEmotionCardBinding,
    private val onCardClick:(EmotionCardModel)->Unit) :
    BaseViewHolder<LogbookEmotionCardBinding, EmotionCardModel>(binding) {
    @SuppressLint("SetTextI18n")
    override fun onBinding(item: EmotionCardModel) = with(binding) {
        root.setOnClickListener{onCardClick(item)}
        background.background = item.background
        textDataTime.text = "${item.date}, ${item.time}"
        cardFilling.text = item.emotion
        emotionSrc.setImageDrawable(item.icon)
    }
}