package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.EmotionIndicatorBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.EmotionIndicatorHolder
import com.example.mydiary.presentation.models.EmotionIndicatorModel

class EmotionIndicatorDelegate() : Delegate<EmotionIndicatorBinding, EmotionIndicatorModel> {

    override fun isRelativeItem(item: Item): Boolean = item is EmotionIndicatorModel

    override fun getLayoutId(): Int = R.layout.emotion_indicator

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<EmotionIndicatorBinding, EmotionIndicatorModel> =
        EmotionIndicatorHolder(EmotionIndicatorBinding.inflate(layoutInflater, parent, false))

    override fun getDiffUtil(): DiffUtil.ItemCallback<EmotionIndicatorModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<EmotionIndicatorModel>() {
        override fun areItemsTheSame(
            oldItem: EmotionIndicatorModel,
            newItem: EmotionIndicatorModel,
        ) = true

        override fun areContentsTheSame(
            oldItem: EmotionIndicatorModel,
            newItem: EmotionIndicatorModel,
        ) = true
    }

}