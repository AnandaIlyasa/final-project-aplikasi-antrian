package com.finalproject.queue.userFragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalproject.queue.Antrian
import com.finalproject.queue.MainActivity
import com.finalproject.queue.MyAdapter
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentSearchBinding
import com.finalproject.queue.viewmodel.AdminQueueViewModel
import com.finalproject.queue.viewmodel.SearchViewModel
import com.google.firebase.database.*

class SearchFragment : Fragment(), MyAdapter.OnItemClickListener {

    private lateinit var binding: FragmentSearchBinding
    //    val bank = resources.getStringArray(R.array.antrian)
    private lateinit var list: ArrayList<Antrian>
    private lateinit var recyclerView: RecyclerView
    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var adapter: MyAdapter
    private lateinit var viewModel: SearchViewModel

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        recyclerView = binding.list
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        list = ArrayList<Antrian>()
        viewModel.recyclerView = recyclerView
        adapter = MyAdapter(context, list, this)

        database.addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list.clear()
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

        recyclerView.adapter = adapter
        viewModel.adapter = adapter

        binding.searchBar.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })

        return binding.root
    }

    private fun filter(s: String){
        var filteredList = ArrayList<Antrian>()

        for(item in list){
            if(item.nama.toLowerCase().contains(s.toLowerCase())){
                filteredList.add(item)
            }
        }
        adapter.filterList(filteredList)
    }

    override fun onItemClick(item: Antrian) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.activity)
        if((activity as MainActivity)!!.dataUser == null){
            builder.setTitle("Mengambil Antrian")
            builder.setMessage("Instansi : ${item.nama}\n" +
                    "Sedang Diproses : ${item.nomor}\n" +
                    "Nomor Anda : ${item.jumlah?.plus(1)}")
            builder.setCancelable(false)
            builder.setPositiveButton("Ya", DialogInterface.OnClickListener { _, i ->
                database.child(item.nama).child("jumlah").get().addOnSuccessListener {
                    viewModel._jumlah.value = it.getValue(Int::class.java)
                    viewModel.tambah(item)
                    adapter.notifyDataSetChanged()
                }
                (activity as MainActivity)!!.dataUser = item
                binding.searchBar.findNavController().navigate(R.id.action_searchFragment_to_queueFragment)
            })
            builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
        } else{
            builder.setTitle("Anda sudah mempunyai antrian")
            builder.setMessage("Kembali ke antrian yang sudah ada ?")
            builder.setCancelable(false)
            builder.setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
                binding.searchBar.findNavController().navigate(R.id.action_searchFragment_to_queueFragment)
            })
            builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}