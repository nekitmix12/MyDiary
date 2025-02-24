package com.example.mydiary.presentation.models

import android.graphics.drawable.Drawable
import com.example.mydiary.presentation.adapters.Item

data class ProfileModel(
    val profileImg: Drawable,
    val name: String
):Item
