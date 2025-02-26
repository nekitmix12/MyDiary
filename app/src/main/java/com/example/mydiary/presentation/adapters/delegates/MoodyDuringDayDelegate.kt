package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.StatisticsMoodDuringDayBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.EmotionCategoryHolder
import com.example.mydiary.presentation.adapters.holders.MoodyDuringDayHolder
import com.example.mydiary.presentation.models.MoodyDuringDayModel

class MoodyDuringDayDelegate() :
    Delegate<StatisticsMoodDuringDayBinding, MoodyDuringDayModel> {

    override fun isRelativeItem(item: Item): Boolean = item is MoodyDuringDayModel

    override fun getLayoutId(): Int = R.layout.statistics_mood_during_day

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<StatisticsMoodDuringDayBinding, MoodyDuringDayModel> =
        MoodyDuringDayHolder(
            StatisticsMoodDuringDayBinding.inflate(
                layoutInflater, parent, false
            )
        )

    override fun getDiffUtil(): DiffUtil.ItemCallback<MoodyDuringDayModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<MoodyDuringDayModel>() {
        override fun areItemsTheSame(oldItem: MoodyDuringDayModel, newItem: MoodyDuringDayModel) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MoodyDuringDayModel,
            newItem: MoodyDuringDayModel,
        ) = true
    }

}