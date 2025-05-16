package com.example.mydiary.presentation.fragments.setting_feature

import dagger.Subcomponent

@Subcomponent
interface SettingFragmentComponent {

    fun inject(settingsFragment: SettingsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create()
    }
}