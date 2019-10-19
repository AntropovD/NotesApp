package com.antropov.talk.contactlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antropov.talk.data.Item
import com.antropov.talk.data.Repository

class ContactListViewModel : ViewModel() {

  private val _counterValue = MutableLiveData<Int>()

  val counterValue: LiveData<Int>
    get() = _counterValue

  private val _items = MutableLiveData<List<Item>>()

  // The external LiveData interface to the property is immutable, so only this class can modify
  val items: LiveData<List<Item>>
    get() = _items

  init {
    _counterValue.value = 0
    _items.value = Repository().array.toList()
  }

  fun increment() {
    _counterValue.value = _counterValue.value?.plus(1)
  }

  fun clear() {
    _counterValue.value = 0
  }
}
