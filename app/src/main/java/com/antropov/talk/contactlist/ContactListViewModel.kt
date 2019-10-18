package com.antropov.talk.contactlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactListViewModel : ViewModel() {

  private val _counterValue = MutableLiveData<Int>()

  val counterValue: LiveData<Int>
    get() = _counterValue

  init {
    _counterValue.value = 0
  }

  fun increment() {
    _counterValue.value = _counterValue.value?.plus(1)
  }

  fun clear() {
    _counterValue.value = 0
  }
}
