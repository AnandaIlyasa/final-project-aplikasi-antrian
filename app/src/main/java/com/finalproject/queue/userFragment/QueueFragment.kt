package com.finalproject.queue.userFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.finalproject.queue.Antrian
import com.finalproject.queue.MainActivity
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentQueueBinding
import com.finalproject.queue.viewmodel.AdminQueueViewModel
import com.finalproject.queue.viewmodel.QueueViewModel
import com.google.firebase.database.*

class QueueFragment : Fragment() {

    private lateinit var binding: FragmentQueueBinding
    private lateinit var database: DatabaseReference
    private lateinit var viewModel: QueueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_queue, container, false)
        database = FirebaseDatabase.getInstance().reference
        viewModel = ViewModelProvider(this).get(QueueViewModel::class.java)
        binding.noSaya.text = (activity as MainActivity)!!.dataUser!!.jumlah?.plus(1).toString()
        var data = (activity as MainActivity)!!.dataUser
        database.child(data!!.nama).child("nomor").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                viewModel._nomor.value = dataSnapshot.getValue(Int::class.java)
                binding.diproses.text = viewModel._nomor.value.toString()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.i("Admin", "Failed to read value.", error.toException())
            }
        })
        return binding.root
    }
}