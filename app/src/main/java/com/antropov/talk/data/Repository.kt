package com.antropov.talk.data

class Repository {
  val array : Array<Item>

  init {
    array = Array(10) {i -> Item(i, "Item #$i")}
  }
}