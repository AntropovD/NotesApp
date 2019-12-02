package com.antropov.notesapp.data

import com.antropov.notesapp.data.entity.Note
import com.antropov.notesapp.data.repository.NotesRepository

class FakeNotesRepository : NotesRepository {

  private val notes = mutableListOf<Note>()

  override suspend fun getNotes(): List<Note> {
    TODO("not implemented")
  }

  override suspend fun getNote(noteId: Int): Note? {
    TODO("not implemented")
  }

  override suspend fun addNote(note: Note) {
    notes.add(note)
  }

  override suspend fun updateNote(note: Note) {
    val savedNote = notes.find { it.noteId == note.noteId }
    savedNote?.let {
      savedNote.title = note.title
      savedNote.description = note.description
      savedNote.dateTime = note.dateTime
    }
  }

  override suspend fun clearNotes() {
    notes.clear()
  }
}
