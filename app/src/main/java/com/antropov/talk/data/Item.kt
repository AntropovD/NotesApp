package com.antropov.talk.data

data class Item(val id: Int, val description: String) {

  constructor(id: Int) : this(id, "My description #$id")
}