package com.finalproject.queue.viewmodel

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var isUser = false
    var isAdmin = false

    override fun onCleared() {
        super.onCleared()
    }
}