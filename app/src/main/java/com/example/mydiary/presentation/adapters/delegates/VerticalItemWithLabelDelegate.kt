package com.example.mydiary.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.StatisticsEmotionsBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.VerticalItemWithLabelHolder
import com.example.mydiary.presentation.models.VerticalItemWithLabelModel

class VerticalItemWithLabelDelegate(
    private val delegatesList: List<Delegate<*, *>>,
) : Delegate<StatisticsEmotionsBinding, VerticalItemWithLabelModel> {

    override fun isRelativeItem(item: Item): Boolean = item is VerticalItemWithLabelModel

    override fun getLayoutId(): Int = R.layout.statistics_emotions

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<StatisticsEmotionsBinding, VerticalItemWithLabelModel> {
        val binding = StatisticsEmotionsBinding.inflate(layoutInflater, parent, false)
        return VerticalItemWithLabelHolder(binding, delegatesList)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<VerticalItemWithLabelModel>() {
        override fun areItemsTheSame(oldItem: VerticalItemWithLabelModel, newItem: VerticalItemWithLabelModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: VerticalItemWithLabelModel, newItem: VerticalItemWithLabelModel) =
            oldItem == newItem

    }

}