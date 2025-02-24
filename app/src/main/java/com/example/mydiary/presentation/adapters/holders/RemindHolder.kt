package com.example.mydiary.presentation.adapters.holders

import com.example.mydiary.databinding.SettingsRemindBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.RemindModel

class RemindHolder(
    binding: SettingsRemindBinding,
    private val onClickDelete: (RemindModel) -> Unit,
) :
    BaseViewHolder<SettingsRemindBinding, RemindModel>(binding) {
    override fun onBinding(item: RemindModel) = with(binding) {
        remindData.text = item.data
        deleteRemind.setOnClickListener { onClickDelete(item) }
    }
}