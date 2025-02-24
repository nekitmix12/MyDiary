package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.LogbookLabelBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.LabelHolder
import com.example.mydiary.presentation.models.LabelModel

class LabelDelegate : Delegate<LogbookLabelBinding, LabelModel> {

    override fun isRelativeItem(item: Item): Boolean = item is LabelModel

    override fun getLayoutId(): Int = R.layout.logbook_label

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<LogbookLabelBinding, LabelModel> =
        LabelHolder(LogbookLabelBinding.inflate(layoutInflater, parent, false))

    override fun getDiffUtil(): DiffUtil.ItemCallback<LabelModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<LabelModel>() {
        override fun areItemsTheSame(oldItem: LabelModel, newItem: LabelModel) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: LabelModel, newItem: LabelModel) =
            true
    }

}