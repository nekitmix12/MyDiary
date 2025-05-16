package com.example.mydiary.presentation.entrance_activity.di

import com.example.mydiary.presentation.entrance_activity.EntranceActivity
import dagger.Subcomponent

@Subcomponent(modules = [GoogleApiModule::class])
interface EntranceActivityComponent {

    fun inject(entranceActivity: EntranceActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): EntranceActivityComponent
    }
}