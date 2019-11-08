package com.antropov.talk.demo

import androidx.lifecycle.ViewModel
import com.antropov.talk.di.ViewModelBuilder
import com.antropov.talk.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DemoModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun demoFragment(): DemoFragment

    @Binds
    @IntoMap
    @ViewModelKey(DemoViewModel::class)
    abstract fun bindViewModel(viewmodel: DemoViewModel): ViewModel
}