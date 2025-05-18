package com.example.mydiary.presentation.features.statistics

import dagger.Subcomponent

@Subcomponent
interface StatisticsFragmentComponent {

    fun inject(settingsFragment: StatisticsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): StatisticsFragmentComponent
    }
}