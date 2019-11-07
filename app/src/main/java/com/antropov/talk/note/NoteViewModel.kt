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

    private var isNewNote = false
    private var noteId = -1

    init {
        dateTime.value = getDateTime()
    }

    fun addNote(title: String, description: String) {
        if (title.isEmpty() || description.isEmpty()) {
            _snackbarErrorEvent.value = Event(Unit)
            return
        }
        _noteAddedEvent.value = Event(Unit)
        if (isNewNote) {
            repository.increment(title, description, dateTime.value!!)
        } else {
            repository.updateItem(noteId, title, description, dateTime.value!!)
        }
    }

    fun start(noteId: Int) {
        if (noteId == -1) {
            isNewNote = true
            return
        }

        val item = repository.getNote(noteId)
        this.noteId = noteId
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