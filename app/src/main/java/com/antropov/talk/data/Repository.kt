package com.antropov.talk.data

class Repository {

  var index = 0

  val items: MutableList<Item> = ArrayList()


  fun increment() {
    index += 1
    items.add(Item(index))
  }

  fun clear() {
    index = 0
    items.clear()
  }
}