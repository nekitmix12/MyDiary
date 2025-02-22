package com.example.mydiary.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.databinding.LogbookEmotionCardBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.models.EmotionCardModel

class EmotionDelegate: Delegate<LogbookEmotionCardBinding,EmotionCardModel> {
    override fun isRelativeItem(item: Item): Boolean {
        TODO("Not yet implemented")
    }

    override fun getLayoutId(): Int {
        TODO("Not yet implemented")
    }

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<LogbookEmotionCardBinding, EmotionCardModel> {
        TODO("Not yet implemented")
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<EmotionCardModel> {
        TODO("Not yet implemented")
    }
}