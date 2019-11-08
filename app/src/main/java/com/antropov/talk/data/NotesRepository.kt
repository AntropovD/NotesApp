package com.antropov.talk.data

interface NotesRepository {

    fun getNotes(): List<Note>

    fun getNote(id: Int): Note?

    fun increment(title: String, description: String, dateTime: String)

    fun clear()

    fun updateItem(noteId: Int, title: String, description: String, dateTime: String)
}