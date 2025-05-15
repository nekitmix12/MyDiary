package com.example.mydiary.presentation.di.subcomponents

import dagger.Subcomponent

@Subcomponent
interface EntranceActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EntranceActivityComponent
    }
}