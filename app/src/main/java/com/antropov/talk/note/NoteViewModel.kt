package com.antropov.talk.note

import androidx.lifecycle.ViewModel
import java.util.Calendar

class NoteViewModel : ViewModel() {

  val dateTime: String
    get() {
      return Calendar.getInstance().time.toString()
    }
}
