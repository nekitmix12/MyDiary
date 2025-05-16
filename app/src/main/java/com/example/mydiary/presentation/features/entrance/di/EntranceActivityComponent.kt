package com.example.mydiary.presentation.features.entrance.di

import com.example.mydiary.presentation.features.entrance.EntranceActivity
import dagger.Subcomponent

@Subcomponent(modules = [GoogleApiModule::class])
interface EntranceActivityComponent {

    fun inject(entranceActivity: EntranceActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): EntranceActivityComponent
    }
}