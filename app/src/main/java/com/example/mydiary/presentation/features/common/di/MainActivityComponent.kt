package com.example.mydiary.presentation.features.common.di

import com.example.mydiary.presentation.features.add_emotion.AddEmotionFragmentComponent
import com.example.mydiary.presentation.features.logbook.LogbookComponent
import com.example.mydiary.presentation.features.notes.NotesFragmentComponent
import com.example.mydiary.presentation.features.settings.SettingFragmentComponent
import com.example.mydiary.presentation.features.statistics.StatisticsFragmentComponent
import dagger.Subcomponent

@MainActivityScope
@Subcomponent
interface MainActivityComponent {
    fun logbookComponent(): LogbookComponent.Factory
    fun notesComponent(): NotesFragmentComponent.Factory
    fun settingsComponent(): SettingFragmentComponent.Factory
    fun statisticsComponent(): StatisticsFragmentComponent.Factory
    fun addEmotionsComponent(): AddEmotionFragmentComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }
}