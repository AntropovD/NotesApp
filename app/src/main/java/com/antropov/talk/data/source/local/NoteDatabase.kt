package com.antropov.talk.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antropov.talk.data.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

  abstract fun notesDao(): NotesDao
}
