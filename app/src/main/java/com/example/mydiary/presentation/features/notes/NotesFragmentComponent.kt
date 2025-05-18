package com.example.mydiary.presentation.features.notes

import dagger.Subcomponent

@Subcomponent
interface NotesFragmentComponent {

    fun inject(settingsFragment: NotesFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): NotesFragmentComponent
    }
}