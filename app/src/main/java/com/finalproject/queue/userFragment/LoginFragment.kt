package com.finalproject.queue.userFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.finalproject.queue.MainActivity
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.login.setOnClickListener {
            if(binding.userRole.isChecked){
                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_homeFragment)
            } else if (binding.adminRole.isChecked){
                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_adminHomeFragment)
            }
        }
        Log.i("info", "oncreateview")
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.i("info", "onstart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("info", "onpause")
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}