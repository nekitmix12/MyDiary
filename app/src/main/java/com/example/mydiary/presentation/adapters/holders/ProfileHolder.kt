package com.example.mydiary.presentation.adapters.holders

import com.example.mydiary.databinding.SettingsProfileBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.ProfileModel

class ProfileHolder(
    binding: SettingsProfileBinding,
    private val onProfileClick: () -> Unit
) :
    BaseViewHolder<SettingsProfileBinding, ProfileModel>(binding) {
    override fun onBinding(item: ProfileModel) = with(binding) {
        shapeableImageView.setImageDrawable(item.profileImg)
        profileName.text = item.name
        shapeableImageView.setOnClickListener { onProfileClick() }
    }
}