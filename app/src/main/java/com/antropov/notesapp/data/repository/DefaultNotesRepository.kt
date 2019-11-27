package com.antropov.notesapp.data.repository

import com.antropov.notesapp.data.source.NotesDataSource
import com.antropov.notesapp.data.entity.Note
import javax.inject.Inject

class DefaultNotesRepository @Inject constructor(
  private val notesDataSource: NotesDataSource
) : NotesRepository {

  override suspend fun getNotes(): List<Note> {
    return notesDataSource.getNotes()
  }

  override suspend fun getNote(noteId: Int): Note? {
    return notesDataSource.getNote(noteId)
  }

  override suspend fun addNote(note: Note) {
    notesDataSource.addNote(note)
  }

  override suspend fun updateNote(note: Note) {
    notesDataSource.updateNote(note)
  }

  override suspend fun clearNotes() {
    notesDataSource.clearNotes()
  }
}