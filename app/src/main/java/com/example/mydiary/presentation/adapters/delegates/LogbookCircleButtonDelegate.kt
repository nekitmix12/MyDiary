package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.LogbookCycrleButtonBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.LogbookCircleButtonHolder
import com.example.mydiary.presentation.models.CircleButtonModel

class LogbookCircleButtonDelegate(
    private val onClick: () -> Unit,
) : Delegate<LogbookCycrleButtonBinding, CircleButtonModel> {

    override fun isRelativeItem(item: Item): Boolean = item is CircleButtonModel

    override fun getLayoutId(): Int = R.layout.logbook_cycrle_button

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<LogbookCycrleButtonBinding, CircleButtonModel> =
        LogbookCircleButtonHolder(
            LogbookCycrleButtonBinding.inflate(layoutInflater, parent, false),
            onClick
        )

    override fun getDiffUtil(): DiffUtil.ItemCallback<CircleButtonModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<CircleButtonModel>() {
        override fun areItemsTheSame(oldItem: CircleButtonModel, newItem: CircleButtonModel) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CircleButtonModel, newItem: CircleButtonModel) =
            true
    }

}