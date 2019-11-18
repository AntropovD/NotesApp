package com.antropov.notesapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.antropov.notesapp.data.Note

@Dao
interface NotesDao {

  @Query("SELECT * FROM notes")
  fun getNotes(): List<Note>

  @Query("SELECT * FROM notes where noteId = :noteId")
  fun getNote(noteId: Int): Note?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addNote(note: Note)

  @Update
  fun updateNote(note: Note)

  @Query("DELETE FROM notes")
  fun clearNotes()
}