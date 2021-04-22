package com.finalproject.queue.adminFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentCreateQueueBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [CreateQueueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateQueueFragment : Fragment() {

    private lateinit var binding: FragmentCreateQueueBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_queue, container, false)
        binding.batal.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
        return binding.root
    }
}