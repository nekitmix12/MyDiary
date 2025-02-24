package com.example.mydiary.presentation.adapters.holders

import com.example.mydiary.databinding.SettingsProfileBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.ProfileModel

class ProfileHolder(
    binding: SettingsProfileBinding,
) :
    BaseViewHolder<SettingsProfileBinding, ProfileModel>(binding) {
    override fun onBinding(item: ProfileModel) = with(binding) {
        ShapeableImageView.setImageDrawable(item.profileImg)
        profileName.text = item.name
    }
}