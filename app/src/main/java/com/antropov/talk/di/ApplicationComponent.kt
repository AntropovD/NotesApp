package com.antropov.talk.di

import android.content.Context
import com.antropov.talk.NotesApplication
import com.antropov.talk.demo.DemoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DemoModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<NotesApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}