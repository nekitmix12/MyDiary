package com.example.mydiary.presentation.di.fragment_components

import com.example.mydiary.presentation.fragments.LogbookFragment
import dagger.Subcomponent

@Subcomponent
interface LogbookComponent {
    fun inject(fragment: LogbookFragment)
}

