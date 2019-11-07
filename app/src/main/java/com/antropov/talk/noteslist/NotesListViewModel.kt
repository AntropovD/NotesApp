package com.antropov.talk.noteslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antropov.talk.data.Note
import com.antropov.talk.data.NoteRepository
import com.antropov.talk.util.Event

class NotesListViewModel : ViewModel() {

  private val repository = NoteRepository.getInstance()

  private val _items = MutableLiveData<List<Note>>()
  val items: LiveData<List<Note>>
    get() = _items

  private val _openNoteEvent = MutableLiveData<Event<Int>>()
  val openNoteEvent: LiveData<Event<Int>>
    get() = _openNoteEvent

  init {
    _items.value = repository.notes
  }

  fun clearView() {
    repository.clear()
    _items.value = listOf()
  }
}
