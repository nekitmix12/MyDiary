package com.example.mydiary.presentation.di.subcomponents

import com.example.mydiary.presentation.di.MainActivityScope
import com.example.mydiary.presentation.di.fragment_components.LogbookComponent
import dagger.Subcomponent

@MainActivityScope
@Subcomponent
interface MainActivityComponent {
    fun logBookComponent(): LogbookComponent

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }
}