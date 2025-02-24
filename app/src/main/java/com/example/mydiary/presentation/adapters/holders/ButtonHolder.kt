package com.example.mydiary.presentation.adapters.holders

import com.example.mydiary.databinding.SettingsButtonBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.ButtonModel

class ButtonHolder(
    binding: SettingsButtonBinding,
    private val onClick: () -> Unit,
) :
    BaseViewHolder<SettingsButtonBinding, ButtonModel>(binding) {
    override fun onBinding(item: ButtonModel) = with(binding) {
        button.text = item.text
        button.setOnClickListener { onClick() }
    }
}