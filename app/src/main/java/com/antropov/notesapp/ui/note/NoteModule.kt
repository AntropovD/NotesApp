package com.antropov.notesapp.ui.note

import androidx.lifecycle.ViewModel
import com.antropov.notesapp.di.viewmodelfactory.ViewModelBuilder
import com.antropov.notesapp.di.viewmodelfactory.ViewModelKey
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
  abstract fun bindViewModel(viewModel: NoteViewModel): ViewModel
}