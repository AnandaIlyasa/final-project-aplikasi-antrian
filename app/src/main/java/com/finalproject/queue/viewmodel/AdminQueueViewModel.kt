package com.finalproject.queue.viewmodel

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class AdminQueueViewModel : ViewModel() {

    private val _nomor = MutableLiveData<Int>()
    val nomor: LiveData<Int>
        get() = _nomor
    init {
        _nomor.value = 0
    }
    fun next(){
        _nomor.value = (_nomor.value)?.plus(1)
    }
    fun prev(){
        _nomor.value = (_nomor.value)?.minus(1)
    }
    override fun onCleared() {
        super.onCleared()
    }
}