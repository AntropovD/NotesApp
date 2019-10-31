package com.antropov.talk.note

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.antropov.talk.data.Repository
import java.util.Calendar

class NoteViewModel : ViewModel() {

  private val repository = Repository.getInstance()

  fun increment(title: Editable, description: Editable) {
    repository.increment(title.toString(), description.toString())
  }

  val dateTime: String
    get() {
      return Calendar.getInstance().time.toString()
    }
}
