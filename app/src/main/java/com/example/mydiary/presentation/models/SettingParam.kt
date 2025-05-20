package com.example.mydiary.presentation.models

sealed interface SettingParam {
    data object IsSendingRemind : SettingParam
    data object IsUseFingerprint : SettingParam
}