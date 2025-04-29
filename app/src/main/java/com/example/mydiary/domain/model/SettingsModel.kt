package com.example.mydiary.domain.model

data class SettingsModel(
    val imageUrl: String,
    val isSendRemindOn: Boolean,
    val isUseFingerprint: Boolean,
    val name: String,
)
