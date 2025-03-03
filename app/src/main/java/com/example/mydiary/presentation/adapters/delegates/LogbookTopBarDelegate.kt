package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.LogbookTopBarBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.LogbookTopBarHolder
import com.example.mydiary.presentation.models.LogbookTopBarModel

class LogbookTopBarDelegate(
) : Delegate<LogbookTopBarBinding, LogbookTopBarModel> {

    override fun isRelativeItem(item: Item): Boolean = item is LogbookTopBarModel

    override fun getLayoutId(): Int = R.layout.logbook_top_bar

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<LogbookTopBarBinding, LogbookTopBarModel> =
        LogbookTopBarHolder(LogbookTopBarBinding.inflate(layoutInflater, parent, false))

    override fun getDiffUtil(): DiffUtil.ItemCallback<LogbookTopBarModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<LogbookTopBarModel>() {
        override fun areItemsTheSame(oldItem: LogbookTopBarModel, newItem: LogbookTopBarModel) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: LogbookTopBarModel, newItem: LogbookTopBarModel) =
            true
    }

}