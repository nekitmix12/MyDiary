package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.StatisticsEmotionByCategoryBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.EmotionCategoryHolder
import com.example.mydiary.presentation.models.EmotionCategoryModel

class EmotionCategoryDelegate() :
    Delegate<StatisticsEmotionByCategoryBinding, EmotionCategoryModel> {

    override fun isRelativeItem(item: Item): Boolean = item is EmotionCategoryModel

    override fun getLayoutId(): Int = R.layout.statistics_emotion_by_category

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<StatisticsEmotionByCategoryBinding, EmotionCategoryModel> =
        EmotionCategoryHolder(
            StatisticsEmotionByCategoryBinding.inflate(
                layoutInflater, parent, false
            )
        )

    override fun getDiffUtil(): DiffUtil.ItemCallback<EmotionCategoryModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<EmotionCategoryModel>() {
        override fun areItemsTheSame(oldItem: EmotionCategoryModel, newItem: EmotionCategoryModel) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: EmotionCategoryModel,
            newItem: EmotionCategoryModel,
        ) = true
    }

}