package com.antropov.notesapp.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antropov.notesapp.data.Note
import com.antropov.notesapp.data.NotesRepository
import com.antropov.notesapp.util.Event
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Calendar
import javax.inject.Inject

class NoteViewModel @Inject constructor(
  private val notesRepository: NotesRepository
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

  init {
    dateTime.value = getDateTime()
  }

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
      createNote(title, description, dateTime.value)
    } else {
      updateNote(title, description, dateTime.value, noteId)
    }
  }

  private fun createNote(title: String, description: String, dateTime: String?) {
    val note = Note(title, description, dateTime!!)
    viewModelScope.launch {
      notesRepository.addNote(note)
    }
  }

  private fun updateNote(title: String, description: String, dateTime: String?, noteId: Int) {
    val note = Note(title, description, dateTime ?: "n/a", noteId)
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

  private fun getDateTime(): String = DateFormat.getDateTimeInstance(
      DateFormat.SHORT,
      DateFormat.SHORT
  ).format(Calendar.getInstance().time)
}
