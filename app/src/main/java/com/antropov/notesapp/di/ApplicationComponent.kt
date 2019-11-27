package com.antropov.notesapp.di

import android.content.Context
import com.antropov.notesapp.NotesApplication
import com.antropov.notesapp.ui.NotesActivityModule
import com.antropov.notesapp.ui.note.NoteModule
import com.antropov.notesapp.ui.noteslist.NotesListModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
      ApplicationModule::class,
      AndroidSupportInjectionModule::class,
      NotesActivityModule::class,
      NoteModule::class,
      NotesListModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<NotesApplication> {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance applicationContext: Context): ApplicationComponent
  }
}