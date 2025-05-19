package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.SettingsRemindBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.RemindHolder
import com.example.mydiary.presentation.models.RemindModel

class RemindDelegate(
    private val onClickDelete: (RemindModel) -> Unit,
) : Delegate<SettingsRemindBinding, RemindModel> {

    override fun isRelativeItem(item: Item): Boolean = item is RemindModel

    override fun getLayoutId(): Int = R.layout.settings_remind

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<SettingsRemindBinding, RemindModel> =
        RemindHolder(SettingsRemindBinding.inflate(layoutInflater, parent, false), onClickDelete)

    override fun getDiffUtil(): DiffUtil.ItemCallback<RemindModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<RemindModel>() {
        override fun areItemsTheSame(oldItem: RemindModel, newItem: RemindModel) =
            oldItem == newItem


        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: RemindModel, newItem: RemindModel) =
            oldItem == newItem

    }

}