package com.example.mydiary.presentation.features.settings

import dagger.Subcomponent

@Subcomponent
interface SettingFragmentComponent {

    fun inject(settingsFragment: SettingsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingFragmentComponent
    }
}