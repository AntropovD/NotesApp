package com.antropov.talk.data

class NoteRepository {

  var index = 0

  val notes: MutableList<Note> = ArrayList()

  fun getNote(id: Int): Note? {
    return notes.find {
      it.id == id
    }
  }

  fun increment(title: String, description: String, dateTime: String) {
    index += 1
    notes.add(Note(index, title, description, dateTime))
  }

  fun clear() {
    index = 0
    notes.clear()
  }

  fun updateItem(noteId: Int, title: String, description: String, dateTime: String) {
    val item = notes.find { it.id == noteId }
    item?.let {
      item.title = title
      item.description = description
      item.datetime = dateTime
    }
  }

  companion object {

    @Volatile
    private var INSTANCE: NoteRepository? = null

    fun getInstance(): NoteRepository {
      if (INSTANCE == null) {
        INSTANCE = NoteRepository()
      }
      return INSTANCE as NoteRepository
    }
  }
}