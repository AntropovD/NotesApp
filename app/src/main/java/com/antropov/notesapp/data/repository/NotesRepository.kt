package com.antropov.notesapp.data.repository

import com.antropov.notesapp.data.entity.Note

interface NotesRepository {

  suspend fun getNotes(): List<Note>

  suspend fun getNote(noteId: Int): Note?

  suspend fun addNote(note: Note)

  suspend fun updateNote(note: Note)

  suspend fun clearNotes()
}