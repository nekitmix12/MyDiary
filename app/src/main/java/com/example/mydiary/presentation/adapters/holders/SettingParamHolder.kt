package com.example.mydiary.presentation.adapters.holders

import com.example.mydiary.databinding.SettingsParamBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.SettingParamModel

class SettingParamHolder(
    binding: SettingsParamBinding,
    private val onSwitchClick: (SettingParamModel) -> Unit,
) : BaseViewHolder<SettingsParamBinding, SettingParamModel>(binding) {
    override fun onBinding(item: SettingParamModel) = with(binding) {
        paramIconZ.setImageDrawable(item.icon)
        paramText.text = item.test
        paramSwitch.isChecked = item.state
        paramSwitch.setOnClickListener { onSwitchClick(item) }
    }
}