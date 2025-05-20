package com.example.mydiary.presentation.features.add_emotion

import dagger.Subcomponent

@Subcomponent
interface AddEmotionFragmentComponent {

    fun inject(settingsFragment: AddEmotionFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): AddEmotionFragmentComponent
    }
}