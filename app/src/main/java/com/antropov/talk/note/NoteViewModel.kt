package com.antropov.talk.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antropov.talk.data.Repository
import com.antropov.talk.util.Event
import java.text.DateFormat
import java.util.*

class NoteViewModel : ViewModel() {

  private val repository = Repository.getInstance()

  val title = MutableLiveData<String>()

  val description = MutableLiveData<String>()

  val dateTime = MutableLiveData<String>()

  private val _snackbarErrorEvent = MutableLiveData<Event<Unit>>()
  val snackbarErrorEvent: LiveData<Event<Unit>> = _snackbarErrorEvent

  private val _noteAddedEvent = MutableLiveData<Event<Unit>>()
  val noteAddedEvent: LiveData<Event<Unit>> = _noteAddedEvent

  init {
    dateTime.value = getDateTime()
  }

  fun addNote(title: String, description: String) =
    if (title.isEmpty() || description.isEmpty()) {
      _snackbarErrorEvent.value = Event(Unit)
    } else {
      repository.increment(title, description, dateTime.value!!)
      _noteAddedEvent.value = Event(Unit)
    }

  fun start(noteId: Int) {
    if (noteId == -1) {
      return
    }

    val item = repository.getNote(noteId)
    if (item != null) {
      title.value = item.title
      description.value = item.description
      dateTime.value = item.datetime
    }
  }

  private fun getDateTime(): String = DateFormat.getDateTimeInstance(
      DateFormat.SHORT,
      DateFormat.SHORT
    ).format(Calendar.getInstance().time)
}
