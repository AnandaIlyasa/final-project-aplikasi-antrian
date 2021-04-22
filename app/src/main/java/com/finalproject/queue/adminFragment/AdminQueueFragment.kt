package com.finalproject.queue.adminFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentAdminQueueBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [AdminQueueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminQueueFragment : Fragment() {

    private lateinit var binding: FragmentAdminQueueBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_queue, container, false)
    }
}