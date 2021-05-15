package com.finalproject.queue.adminFragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.finalproject.queue.MainActivity
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentAdminHomeBinding
import com.finalproject.queue.userFragment.LoginFragment
import com.finalproject.queue.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [AdminHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminHomeFragment : Fragment() {

    private lateinit var binding: FragmentAdminHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_home, container, false)
        binding.logout.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_adminHomeFragment_to_loginFragment)
        }
        binding.create.setOnClickListener {
            if ((activity as MainActivity)!!.adaAntrian){
                masukKeAntrian()
            }
            else{
                Navigation.findNavController(it).navigate(R.id.action_adminHomeFragment_to_createQueueFragment)
            }
        }
        binding.riwayat.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_adminHomeFragment_to_adminHistoryFragment)
        }
        binding.antrian.setOnClickListener {
            if ((activity as MainActivity)!!.adaAntrian){
                Navigation.findNavController(it).navigate(R.id.action_adminHomeFragment_to_adminQueueFragment)
            }
            else{
                buatAntrian()
            }
        }
        binding.nama.text = (activity as MainActivity)!!.mFirebaseAuth.currentUser.displayName
        (activity as MainActivity).diHome = true
        Log.i("adminhome", "oncreateview")
        return binding.root
    }

    private fun masukKeAntrian(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.activity)
        builder.setTitle("Anda Sudah Membuat Antrian")
        builder.setMessage("Masuk Ke Antrian Yang Sudah Dibuat ?")
        builder.setCancelable(false)
        builder.setPositiveButton("Ya", DialogInterface.OnClickListener { _, i ->
            Navigation.findNavController(binding.create).navigate(R.id.action_adminHomeFragment_to_adminQueueFragment)
        })
        builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })
        val alertDialog = builder.create()
        alertDialog.show()
    }
    private fun buatAntrian(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.activity)
        builder.setTitle("Anda Belum Membuat Antrian")
        builder.setMessage("Buat Antrian ?")
        builder.setCancelable(false)
        builder.setPositiveButton("Ya", DialogInterface.OnClickListener { _, i ->
            Navigation.findNavController(binding.create).navigate(R.id.action_adminHomeFragment_to_createQueueFragment)
        })
        builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })
        val alertDialog = builder.create()
        alertDialog.show()
    }
    override fun onStop() {
        Log.i("adminhome", "stop")
        (activity as MainActivity).diHome = false
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("adminhome", "destroy")
        super.onDestroy()
    }
}