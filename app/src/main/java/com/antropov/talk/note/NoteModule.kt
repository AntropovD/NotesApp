package com.antropov.talk.note

import androidx.lifecycle.ViewModel
import com.antropov.talk.di.ViewModelBuilder
import com.antropov.talk.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class NoteModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun noteFragment(): NoteFragment

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    abstract fun bindViewModel(viewmodel: NoteViewModel): ViewModel
}