package com.finalproject.queue.adminFragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.finalproject.queue.Antrian
import com.finalproject.queue.MainActivity
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentAdminQueueBinding
import com.finalproject.queue.viewmodel.AdminQueueViewModel
import com.google.firebase.database.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [AdminQueueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminQueueFragment : Fragment() {

    private lateinit var binding: FragmentAdminQueueBinding
    private lateinit var viewModel: AdminQueueViewModel
    private lateinit var ref: DatabaseReference
    private lateinit var data: Antrian

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_queue, container, false)
        viewModel = ViewModelProvider(this).get(AdminQueueViewModel::class.java)
        (activity as MainActivity)!!.diAntrian = true
        binding.adminQueueViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        ref = FirebaseDatabase.getInstance().reference
        data = (activity as MainActivity).dataAntrian!!
        if ((activity as MainActivity)!!.diAntrian){
            ref.child(data.nama).child("nomor").addValueEventListener(object :
                    ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    viewModel._nomor.value = dataSnapshot.getValue(Int::class.java)
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.i("Admin", "Failed to read value.", error.toException())
                }
            })

            ref.child(data.nama).child("jumlah").addValueEventListener(object :
                    ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    viewModel._jumlah.value = dataSnapshot.getValue(Int::class.java)
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.i("Admin", "Failed to read value.", error.toException())
                }
            })

            ref.child(data.nama).child("nama").addValueEventListener(object :
                    ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    viewModel._nama.value = dataSnapshot.getValue(String::class.java)
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.i("Admin", "Failed to read value.", error.toException())
                }
            })
        }

        binding.prev.setOnClickListener {
            viewModel.prev(data)
        }
        binding.next.setOnClickListener {
            viewModel.next(data)
        }
        binding.tutup.setOnClickListener {
            tutup()
        }
        Log.i("admin", "admin fragment")

        binding.prev.text = "<<PREV"
        binding.next.text = "NEXT>>"
        return binding.root
    }
    private fun tutup(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.activity)
        builder.setTitle("Tutup Antrian?")
        builder.setCancelable(false)
        builder.setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
            Navigation.findNavController(binding.tutup).popBackStack()
            Toast.makeText(context, "Menutup Antrian...", Toast.LENGTH_SHORT).show()
            (activity as MainActivity)!!.hapusAntrian()
        })
        builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onStop() {
        Log.i("adminantrian", "stop")
        (activity as MainActivity).diAntrian = false
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("adminantrian", "destroy")
        (activity as MainActivity).diAntrian = false
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("adminantrian", "detach")
        super.onDetach()
    }
}