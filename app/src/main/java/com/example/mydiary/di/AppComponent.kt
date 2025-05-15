package com.example.mydiary.di

import android.content.Context
import com.example.mydiary.presentation.di.subcomponents.MainActivityComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [AppBindModule::class, AppProvidesModule::class, DataBaseModule::class],
    dependencies = []
)
interface AppComponent {

    fun mainActivityComponent(): MainActivityComponent.Factory

    @Component.Factory
    interface Factory {


        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }

//    fun inject()

}

