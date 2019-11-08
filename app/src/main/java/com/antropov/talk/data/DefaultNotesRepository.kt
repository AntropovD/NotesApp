package com.antropov.talk.data

import javax.inject.Inject

class DefaultNotesRepository @Inject constructor() : NotesRepository {

  var index = 0
  val _notes: MutableList<Note> = ArrayList()

  override fun getNotes(): List<Note> {
    return _notes
  }

  override fun getNote(id: Int): Note? {
    return _notes.find {
      it.id == id
    }
  }

  override fun increment(title: String, description: String, dateTime: String) {
    index += 1
    _notes.add(Note(index, title, description, dateTime))
  }

  override fun clear() {
    index = 0
    _notes.clear()
  }

  override fun updateItem(noteId: Int, title: String, description: String, dateTime: String) {
    val item = _notes.find { it.id == noteId }
    item?.let {
      item.title = title
      item.description = description
      item.datetime = dateTime
    }
  }

  companion object {

    @Volatile
    private var INSTANCE: DefaultNotesRepository? = null

    fun getInstance(): DefaultNotesRepository {
      if (INSTANCE == null) {
        INSTANCE = DefaultNotesRepository()
      }
      return INSTANCE as DefaultNotesRepository
    }
  }
}