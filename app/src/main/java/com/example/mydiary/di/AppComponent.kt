package com.example.mydiary.di

import android.content.Context
import com.example.mydiary.presentation.features.common.di.MainActivityComponent
import com.example.mydiary.presentation.features.entrance.di.EntranceActivityComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [AppBindModule::class, AppProvidesModule::class, DataBaseModule::class],
    dependencies = []
)
interface AppComponent {

    fun mainActivityComponent(): MainActivityComponent.Factory

    fun entranceActivityComponent(): EntranceActivityComponent.Factory

    @Component.Factory
    interface Factory {


        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }

//    fun inject()

}

