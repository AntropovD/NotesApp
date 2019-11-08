package com.antropov.talk.noteslist

import androidx.lifecycle.ViewModel
import com.antropov.talk.di.ViewModelBuilder
import com.antropov.talk.di.ViewModelKey
import com.antropov.talk.note.NoteFragment
import com.antropov.talk.note.NoteViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class NotesListModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun notesListFragment(): NotesListFragment

    @Binds
    @IntoMap
    @ViewModelKey(NotesListViewModel::class)
    abstract fun bindViewModel(viewmodel: NotesListViewModel): ViewModel
}