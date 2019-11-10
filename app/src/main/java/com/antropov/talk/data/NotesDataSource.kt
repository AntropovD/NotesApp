package com.antropov.talk.data

interface NotesDataSource {

  suspend fun getNotes(): List<Note>

  suspend fun getNote(noteId: Int): Note?

  suspend fun addNote(note: Note)

  suspend fun updateNote(note: Note)

  suspend fun clearNotes()
}