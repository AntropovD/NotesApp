package com.antropov.talk.note

import androidx.lifecycle.ViewModel
import com.antropov.talk.data.Repository
import java.util.Calendar

class NoteViewModel : ViewModel() {

  private val repository = Repository.getInstance()

  fun increment() {
    repository.increment()
  }

  val dateTime: String
    get() {
      return Calendar.getInstance().time.toString()
    }
}
