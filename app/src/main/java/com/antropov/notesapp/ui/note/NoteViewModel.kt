package com.antropov.notesapp.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antropov.notesapp.data.entity.Note
import com.antropov.notesapp.data.repository.NotesRepository
import com.antropov.notesapp.util.DateTimeProvider
import com.antropov.notesapp.util.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(
  private val notesRepository: NotesRepository,
  private val dateTimeProvider: DateTimeProvider
) : ViewModel() {

  val title = MutableLiveData<String>()

  val description = MutableLiveData<String>()

  val dateTime = MutableLiveData<String>()

  private val _snackbarErrorEvent = MutableLiveData<Event<Unit>>()
  val snackbarErrorEvent: LiveData<Event<Unit>> = _snackbarErrorEvent

  private val _noteAddedEvent = MutableLiveData<Event<Unit>>()
  val noteAddedEvent: LiveData<Event<Unit>> = _noteAddedEvent

  private var isNewNote = false
  private var noteId = -1

  fun addNote(
    title: String,
    description: String
  ) {
    if (title.isEmpty() || description.isEmpty()) {
      _snackbarErrorEvent.value = Event(Unit)
      return
    }
    _noteAddedEvent.value = Event(Unit)
    if (isNewNote) {
      createNote(title, description, dateTimeProvider.getDateTime())
    } else {
      updateNote(title, description, dateTimeProvider.getDateTime(), noteId)
    }
  }

  private fun createNote(title: String, description: String, dateTime: String?) {
    val note =
        Note(title, description, dateTime!!)
    viewModelScope.launch {
      notesRepository.addNote(note)
    }
  }

  private fun updateNote(title: String, description: String, dateTime: String?, noteId: Int) {
    val note = Note(title, description,
        dateTime ?: "n/a", noteId)
    viewModelScope.launch {
      notesRepository.updateNote(note)
    }
  }

  fun start(noteId: Int) {
    if (noteId == -1) {
      isNewNote = true
      return
    }

    viewModelScope.launch {
      val item = notesRepository.getNote(noteId)
      this@NoteViewModel.noteId = noteId
      if (item != null) {
        title.value = item.title
        description.value = item.description
        dateTime.value = item.dateTime
      }
    }
  }
}
