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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.finalproject.queue.MainActivity
import com.finalproject.queue.R
import com.finalproject.queue.databinding.FragmentLoginBinding
import com.finalproject.queue.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        if(viewModel.isUser){
            this.view?.let { Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_homeFragment) }
        } else if (viewModel.isAdmin){
            this.view?.let { Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_adminHomeFragment) }
        }
        binding.login.setOnClickListener {
            if(binding.userRole.isChecked){
                viewModel.isUser = true
                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_homeFragment)
            } else if (binding.adminRole.isChecked){
                viewModel.isAdmin = true
                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_adminHomeFragment)
            }
        }
        binding.roles.setOnCheckedChangeListener { radioGroup, i ->
            when(radioGroup.checkedRadioButtonId){
                binding.adminRole.id -> binding.tombolRole.text = "Masuk Sebagai ADMIN"
                else -> binding.tombolRole.text = "Masuk Sebagai USER"
            }
        }
        binding.logout.setOnClickListener {
            (activity as MainActivity)!!.signOut()
        }
        Log.i("info", "login oncreateview")
        return binding.root
    }

    fun userExit(){
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.isUser = false
    }
    fun adminExit(){
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.isAdmin = false
    }

    override fun onStart() {
        super.onStart()
        Log.i("info", "onstart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("info", "onpause")
    }

    override fun onResume() {
        super.onResume()
        Log.i("info", "login onresume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("info", "login fragment destroyed")
    }

    override fun onStop() {
        super.onStop()
        Log.i("info", "login onstop")
    }
}