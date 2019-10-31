package com.antropov.talk.data

data class Item(
  val id: Int,
  val title: String,
  val description: String,
  val datetime: String
) {
  constructor(id: Int) : this(
    id,
    "Title #$id",
    "My description #$id",
    "datetime #$id"
  )
}