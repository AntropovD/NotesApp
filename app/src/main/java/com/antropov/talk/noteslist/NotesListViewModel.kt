package com.antropov.talk.noteslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antropov.talk.data.Note
import com.antropov.talk.data.NotesRepository
import com.antropov.talk.util.Event
import javax.inject.Inject

class NotesListViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {

  private val _items = MutableLiveData<List<Note>>()
  val items: LiveData<List<Note>>
    get() = _items

  private val _openNoteEvent = MutableLiveData<Event<Int>>()
  val openNoteEvent: LiveData<Event<Int>>
    get() = _openNoteEvent

  init {
      _items.value = repository.getNotes()
  }

  fun clearView() {
    repository.clear()
    _items.value = listOf()
  }
}
