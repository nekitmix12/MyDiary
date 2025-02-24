package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.SettingsButtonBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.ButtonHolder
import com.example.mydiary.presentation.models.ButtonModel

class ButtonDelegate(
    private val onClick: () -> Unit,
) : Delegate<SettingsButtonBinding, ButtonModel> {

    override fun isRelativeItem(item: Item): Boolean = item is ButtonModel

    override fun getLayoutId(): Int = R.layout.settings_button

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<SettingsButtonBinding, ButtonModel> =
        ButtonHolder(SettingsButtonBinding.inflate(layoutInflater, parent, false),onClick)

    override fun getDiffUtil(): DiffUtil.ItemCallback<ButtonModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<ButtonModel>() {
        override fun areItemsTheSame(oldItem: ButtonModel, newItem: ButtonModel) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ButtonModel, newItem: ButtonModel) =
            true
    }

}