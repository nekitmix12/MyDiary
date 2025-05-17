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
import com.example.mydiary.presentation.models.CircleButton

class LogbookCircleButtonDelegate(
    private val onClick: () -> Unit,
) : Delegate<LogbookCycrleButtonBinding, CircleButton> {

    override fun isRelativeItem(item: Item): Boolean = item is CircleButton

    override fun getLayoutId(): Int = R.layout.logbook_cycrle_button

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<LogbookCycrleButtonBinding, CircleButton> =
        LogbookCircleButtonHolder(
            LogbookCycrleButtonBinding.inflate(layoutInflater, parent, false),
            onClick
        )

    override fun getDiffUtil(): DiffUtil.ItemCallback<CircleButton> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<CircleButton>() {
        override fun areItemsTheSame(oldItem: CircleButton, newItem: CircleButton) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CircleButton, newItem: CircleButton) =
            true
    }

}