package com.antropov.talk.data.source.local

import com.antropov.talk.data.Note
import com.antropov.talk.data.NotesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesLocalDataSource internal constructor(
  private val notesDao: NotesDao,
  private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NotesDataSource {

  override suspend fun getNotes(): List<Note> = withContext(ioDispatcher) {
    notesDao.getNotes()
  }

  override suspend fun getNote(noteId: Int): Note? = withContext(ioDispatcher) {
    notesDao.getNote(noteId)
  }

  override suspend fun addNote(note: Note) = withContext(ioDispatcher) {
    notesDao.addNote(note)
  }

  override suspend fun updateNote(note: Note) = withContext(ioDispatcher) {
    notesDao.updateNote(note)
  }

  override suspend fun clearNotes() = withContext(ioDispatcher) {
    notesDao.clearNotes()
  }
}