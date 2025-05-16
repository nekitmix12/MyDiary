package com.example.mydiary.presentation.adapters

import com.example.mydiary.presentation.models.RemindModel

interface OnRemindClick {
    fun onDeleteClick(remindModel: RemindModel)
}

interface OnParamClick {
    fun onSwitchClock()
}

interface OnProfileClick {
    fun onProfileClick(profileUrl: String)
}

interface On