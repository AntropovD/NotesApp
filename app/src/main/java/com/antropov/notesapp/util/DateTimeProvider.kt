package com.antropov.notesapp.util

import java.text.DateFormat
import java.util.Calendar

class DefaultDateTimeProvider : DateTimeProvider {
  override fun getDateTime(): String =
      DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(
          Calendar.getInstance().time)
}

interface DateTimeProvider {
  fun getDateTime(): String
}
