package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.SettingsParamBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.SettingParamHolder
import com.example.mydiary.presentation.models.SettingParamModel

class SettingParamDelegate(private val onSwitchClick: () -> Unit) :
    Delegate<SettingsParamBinding, SettingParamModel> {

    override fun isRelativeItem(item: Item): Boolean = item is SettingParamModel

    override fun getLayoutId(): Int = R.layout.settings_param

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<SettingsParamBinding, SettingParamModel> =
        SettingParamHolder(SettingsParamBinding.inflate(layoutInflater, parent, false),onSwitchClick)

    override fun getDiffUtil(): DiffUtil.ItemCallback<SettingParamModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<SettingParamModel>() {
        override fun areItemsTheSame(oldItem: SettingParamModel, newItem: SettingParamModel) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: SettingParamModel, newItem: SettingParamModel) =
            true
    }

}