package com.antropov.talk.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DemoViewModel @Inject constructor() : ViewModel() {

    private var _value = MutableLiveData<String>("value")
    val value: LiveData<String>
        get() = _value
}
