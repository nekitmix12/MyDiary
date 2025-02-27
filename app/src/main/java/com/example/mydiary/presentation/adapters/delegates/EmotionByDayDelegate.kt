package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.EmotionLogsBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.EmotionByDayHolder
import com.example.mydiary.presentation.models.EmotionByDayModel

class EmotionByDayDelegate(
) : Delegate<EmotionLogsBinding, EmotionByDayModel> {

    override fun isRelativeItem(item: Item): Boolean = item is EmotionByDayModel

    override fun getLayoutId(): Int = R.layout.emotion_logs

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<EmotionLogsBinding, EmotionByDayModel> =
        EmotionByDayHolder(EmotionLogsBinding.inflate(layoutInflater, parent, false))

    override fun getDiffUtil(): DiffUtil.ItemCallback<EmotionByDayModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<EmotionByDayModel>() {
        override fun areItemsTheSame(oldItem: EmotionByDayModel, newItem: EmotionByDayModel) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: EmotionByDayModel, newItem: EmotionByDayModel) =
            true
    }

}