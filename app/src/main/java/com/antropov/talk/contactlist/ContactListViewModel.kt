package com.antropov.talk.contactlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antropov.talk.data.Item
import com.antropov.talk.data.Repository

class ContactListViewModel : ViewModel() {

  private val repository = Repository()

  private val _items = MutableLiveData<List<Item>>()
  val items: LiveData<List<Item>>
    get() = _items

  init {
    _items.value = listOf()
  }

  fun increment() {
    repository.increment()
    updateLiveData()
  }

  fun clearView() {
    repository.clear()
    updateLiveData()
  }

  private fun updateLiveData() {
    _items.value = repository.items
  }
}
