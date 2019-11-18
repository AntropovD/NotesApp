package com.antropov.notesapp.di

import android.content.Context
import androidx.room.Room
import com.antropov.notesapp.data.DefaultNotesRepository
import com.antropov.notesapp.data.NotesDataSource
import com.antropov.notesapp.data.NotesRepository
import com.antropov.notesapp.data.source.local.NoteDatabase
import com.antropov.notesapp.data.source.local.NotesLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

  @JvmStatic
  @Singleton
  @Provides
  fun provideLocalDataSource(
    database: NoteDatabase,
    ioDispatcher: CoroutineDispatcher
  ): NotesDataSource {
    return NotesLocalDataSource(
        database.notesDao(),
        ioDispatcher
    )
  }

  @JvmStatic
  @Singleton
  @Provides
  fun providesDatabase(context: Context): NoteDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        NoteDatabase::class.java,
        "Notes.db")
        .build()
  }

  @JvmStatic
  @Singleton
  @Provides
  fun provideIoDispatcher() = Dispatchers.IO
}

@Module
abstract class ApplicationModuleBinds {

  @Singleton
  @Binds
  abstract fun bindRepository(repo: DefaultNotesRepository): NotesRepository
}