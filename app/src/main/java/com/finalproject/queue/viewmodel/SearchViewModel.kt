package com.finalproject.queue.viewmodel

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.finalproject.queue.Antrian
import com.finalproject.queue.MyAdapter
import com.google.firebase.database.*

class SearchViewModel() : ViewModel(), Parcelable {

    private val ref: DatabaseReference = FirebaseDatabase.getInstance().reference
    lateinit var adapter: MyAdapter
    lateinit var recyclerView: RecyclerView

    val _jumlah = MutableLiveData<Int>()
    val jumlah: LiveData<Int>
        get() = _jumlah

    constructor(parcel: Parcel) : this() {
    }

    init {
        _jumlah.value = 0
    }

    fun tambah(data: Antrian){
        ref.child(data!!.nama).child("jumlah").setValue(_jumlah.value?.plus(1))
    }

    override fun onCleared() {
        super.onCleared()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchViewModel> {
        override fun createFromParcel(parcel: Parcel): SearchViewModel {
            return SearchViewModel(parcel)
        }

        override fun newArray(size: Int): Array<SearchViewModel?> {
            return arrayOfNulls(size)
        }
    }
}