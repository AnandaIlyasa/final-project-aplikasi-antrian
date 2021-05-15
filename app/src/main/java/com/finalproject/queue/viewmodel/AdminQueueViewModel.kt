package com.finalproject.queue.viewmodel

import android.app.Activity
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.finalproject.queue.Antrian
import com.finalproject.queue.MainActivity
import com.google.firebase.database.*

class AdminQueueViewModel : ViewModel() {
    private val ref: DatabaseReference = FirebaseDatabase.getInstance().reference

    val _nomor = MutableLiveData<Int>()
    val nomor: LiveData<Int>
        get() = _nomor
    init {

    }
    fun next(data: Antrian){
        ref.child(data!!.nama).child("nomor").setValue(_nomor.value?.plus(1))
    }
    fun prev(data: Antrian){
        ref.child(data!!.nama).child("nomor").setValue(_nomor.value?.minus(1))
    }
    override fun onCleared() {
        super.onCleared()
    }
}