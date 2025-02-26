package com.example.mydiary.presentation.adapters.holders

import androidx.constraintlayout.widget.ConstraintSet
import com.example.mydiary.databinding.StatisticsEmotionByCategoryBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.EmotionCategoryModel

class EmotionCategoryHolder(
    binding: StatisticsEmotionByCategoryBinding,
) : BaseViewHolder<StatisticsEmotionByCategoryBinding, EmotionCategoryModel>(binding) {
    override fun onBinding(item: EmotionCategoryModel) = with(binding) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.constrainPercentWidth(redCircle.id, item.emotionsPercent[0].second)
        constraintSet.constrainPercentWidth(yellowCircle.id, item.emotionsPercent[1].second)
        constraintSet.constrainPercentWidth(blueCircle.id, item.emotionsPercent[2].second)
        constraintSet.constrainPercentWidth(greenCircle.id, item.emotionsPercent[3].second)
        constraintSet.applyTo(constraintLayout)

        redCircle.elevation = item.emotionsPercent[0].first
        yellowCircle.elevation = item.emotionsPercent[1].first
        blueCircle.elevation = item.emotionsPercent[2].first
        greenCircle.elevation = item.emotionsPercent[3].first
    }
}