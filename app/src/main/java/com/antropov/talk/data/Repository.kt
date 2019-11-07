package com.antropov.talk.data

class Repository {

  var index = 0

  val items: MutableList<Item> = ArrayList()

  fun getNote(id: Int): Item? {
    return items.find {
      it.id == id
    }
  }

  fun increment(title: String, description: String, dateTime: String) {
    index += 1
    items.add(Item(index, title, description, dateTime))
  }

  fun clear() {
    index = 0
    items.clear()
  }

  fun updateItem(noteId: Int, title: String, description: String, dateTime: String) {
    val item = items.find { it.id == noteId }
    item?.let {
      item.title = title
      item.description = description
      item.datetime = dateTime
    }
  }

  companion object {

    @Volatile
    private var INSTANCE: Repository? = null

    fun getInstance(): Repository {
      if (INSTANCE == null) {
        INSTANCE = Repository()
      }
      return INSTANCE as Repository
    }
  }
}