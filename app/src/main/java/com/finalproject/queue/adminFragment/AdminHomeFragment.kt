package com.finalproject.queue.adminFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.finalproject.queue.MainActivity
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentAdminHomeBinding

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
            Navigation.findNavController(it).navigate(R.id.action_adminHomeFragment_to_createQueueFragment)
        }
        binding.riwayat.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_adminHomeFragment_to_adminHistoryFragment)
        }
        binding.antrian.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_adminHomeFragment_to_adminQueueFragment)
        }
        binding.nama.text = (activity as MainActivity)!!.mFirebaseAuth.currentUser.displayName
        return binding.root
    }
}