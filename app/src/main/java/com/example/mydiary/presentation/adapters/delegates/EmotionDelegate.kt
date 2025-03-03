package com.example.mydiary.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.LogbookEmotionCardBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.EmotionCardHolder
import com.example.mydiary.presentation.models.EmotionCardModel

class EmotionDelegate(private val onCardClick: (EmotionCardModel) -> Unit) :
    Delegate<LogbookEmotionCardBinding, EmotionCardModel> {

    override fun isRelativeItem(item: Item): Boolean = item is EmotionCardModel

    override fun getLayoutId(): Int = R.layout.logbook_emotion_card

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<LogbookEmotionCardBinding, EmotionCardModel> =
        EmotionCardHolder(
            LogbookEmotionCardBinding.inflate(layoutInflater, parent, false),
            onCardClick
        )

    override fun getDiffUtil(): DiffUtil.ItemCallback<EmotionCardModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<EmotionCardModel>() {
        override fun areItemsTheSame(oldItem: EmotionCardModel, newItem: EmotionCardModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: EmotionCardModel, newItem: EmotionCardModel) =
            oldItem == newItem
    }

}