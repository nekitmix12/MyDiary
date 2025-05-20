package com.example.mydiary.domain.model

data class SettingsModel(
    val imageUrl: String = "",
    val isSendRemindOn: Boolean = false,
    val isUseFingerprint: Boolean = false,
    val name: String = "",
    val sex: Int? = null
)
