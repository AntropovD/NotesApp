package com.antropov.notesapp.noteslist

import androidx.lifecycle.ViewModel
import com.antropov.notesapp.di.ViewModelBuilder
import com.antropov.notesapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class NotesListModule {

  @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
  internal abstract fun notesListFragment(): NotesListFragment

  @Binds
  @IntoMap
  @ViewModelKey(NotesListViewModel::class)
  abstract fun bindViewModel(viewModel: NotesListViewModel): ViewModel
}