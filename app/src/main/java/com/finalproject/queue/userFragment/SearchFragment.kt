package com.finalproject.queue.userFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalproject.queue.Antrian
import com.finalproject.queue.MyAdapter
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentSearchBinding
import com.google.firebase.database.*

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
//    val bank = resources.getStringArray(R.array.antrian)
    private var antrian = ArrayList<Antrian?>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var database: DatabaseReference
    private lateinit var adapter: MyAdapter

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        recyclerView = binding.list
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        database = FirebaseDatabase.getInstance().reference

        var list = ArrayList<Antrian>()

        adapter = MyAdapter(context, list)

        recyclerView.adapter = adapter

        database.addValueEventListener(object :
                ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.exists()){
                    for (data in dataSnapshot.children){
                        var nama = data.child("nama").value.toString()
                        var deskripsi = data.child("deskripsi").value.toString()
                        var nomor = data.child("nomor").getValue(Int::class.java)
                        var jumlah = data.child("jumlah").getValue(Int::class.java)
                        list.add(Antrian(nama, deskripsi, nomor, jumlah))
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.i("Admin", "Failed to read value.", error.toException())
            }
        })

        return binding.root
    }
}