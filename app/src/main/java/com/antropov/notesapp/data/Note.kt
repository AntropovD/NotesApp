package com.antropov.notesapp.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    indices = [Index("title")]
)
data class Note @JvmOverloads constructor(
  var title: String = "",
  var description: String = "",
  var dateTime: String = "",
  @PrimaryKey(autoGenerate = true) val noteId: Int? = null
)