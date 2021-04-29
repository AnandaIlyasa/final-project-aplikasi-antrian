package com.finalproject.queue.adminFragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentAdminQueueBinding
import com.finalproject.queue.viewmodel.AdminQueueViewModel
import com.finalproject.queue.viewmodel.LoginViewModel

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_queue, container, false)
        viewModel = ViewModelProvider(this).get(AdminQueueViewModel::class.java)
        binding.adminQueueViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.tutup.setOnClickListener {
            tutup()
        }
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
            Toast.makeText(context, "Menutup Antrian...", Toast.LENGTH_LONG).show()
        })
        builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })
        val alertDialog = builder.create()
        alertDialog.show()
    }
}