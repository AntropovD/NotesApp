package com.antropov.notesapp.di.viewmodelfactory

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilder {
  @Binds
  internal abstract fun bindViewModelFactory(
    factory: ViewModelFactory
  ): ViewModelProvider.Factory
}