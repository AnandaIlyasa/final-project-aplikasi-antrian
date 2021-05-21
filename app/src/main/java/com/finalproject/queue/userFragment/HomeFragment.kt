package com.finalproject.queue.userFragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.finalproject.queue.MainActivity
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentHomeBinding
import com.finalproject.queue.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.logout.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_loginFragment)
        }
        binding.antrianku.setOnClickListener {
            if((activity as MainActivity)!!.dataUser?.equals(null)!!){
                val builder: AlertDialog.Builder = AlertDialog.Builder(this.activity)
                builder.setTitle("Anda Belum Mengambil Antrian")
                builder.setMessage("Cari Antrian ?")
                builder.setCancelable(false)
                builder.setPositiveButton("Ya", DialogInterface.OnClickListener { _, i ->
                    Navigation.findNavController(binding.antrianku).navigate(R.id.action_homeFragment_to_searchFragment)
                })
                builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.cancel()
                })
                val alertDialog = builder.create()
                alertDialog.show()
            }
            else{
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_queueFragment)
            }
        }
        binding.cari.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_searchFragment)
        }
        binding.riwayat.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_historyFragment)
        }
        binding.nama.text = (activity as MainActivity)!!.mFirebaseAuth.currentUser.displayName
        return binding.root
    }
}