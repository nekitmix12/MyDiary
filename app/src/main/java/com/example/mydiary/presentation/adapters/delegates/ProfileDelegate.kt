package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.SettingsProfileBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.ProfileHolder
import com.example.mydiary.presentation.models.ProfileModel

class ProfileDelegate : Delegate<SettingsProfileBinding, ProfileModel> {

    override fun isRelativeItem(item: Item): Boolean = item is ProfileModel

    override fun getLayoutId(): Int = R.layout.settings_profile

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<SettingsProfileBinding, ProfileModel> =
        ProfileHolder(SettingsProfileBinding.inflate(layoutInflater, parent, false))

    override fun getDiffUtil(): DiffUtil.ItemCallback<ProfileModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<ProfileModel>() {
        override fun areItemsTheSame(oldItem: ProfileModel, newItem: ProfileModel) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ProfileModel, newItem: ProfileModel) =
            true
    }

}