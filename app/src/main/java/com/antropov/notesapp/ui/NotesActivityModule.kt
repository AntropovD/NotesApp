package com.antropov.notesapp.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NotesActivityModule {
  
  @ContributesAndroidInjector
  internal abstract fun notesActivity(): NotesActivity
}
