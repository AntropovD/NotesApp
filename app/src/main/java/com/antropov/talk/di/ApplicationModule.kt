package com.antropov.talk.di

import com.antropov.talk.data.DefaultNotesRepository
import com.antropov.talk.data.NotesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideNotesRepository(): NotesRepository {
        return DefaultNotesRepository.getInstance()
    }
}