package com.example.mydiary.presentation.di.fragment_components

import com.example.mydiary.presentation.features.logbook.LogbookFragment
import dagger.Subcomponent

@Subcomponent
interface LogbookComponent {
    fun inject(fragment: LogbookFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): LogbookComponent
    }
}

