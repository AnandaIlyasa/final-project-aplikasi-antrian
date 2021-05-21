package com.finalproject.queue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finalproject.queue.Antrian

class QueueViewModel : ViewModel() {

    val _nomor = MutableLiveData<Int>()
    val nomor: LiveData<Int>
        get() = _nomor
}