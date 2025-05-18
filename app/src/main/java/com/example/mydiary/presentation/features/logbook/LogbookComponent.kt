package com.example.mydiary.presentation.features.logbook

import dagger.Subcomponent

@Subcomponent
interface LogbookComponent {
    fun inject(fragment: LogbookFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): LogbookComponent
    }
}

