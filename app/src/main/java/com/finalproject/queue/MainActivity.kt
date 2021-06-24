package com.finalproject.queue

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.finalproject.queue.databinding.ActivityMainBinding
import com.finalproject.queue.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mSignInClient: GoogleSignInClient
    private lateinit var mBinding: ActivityMainBinding
    // Firebase instance variables
    lateinit var mFirebaseAuth: FirebaseAuth
    var nodeRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    var adaAntrian: Boolean = false
    var dataAntrian: Antrian? =  null
    var diHome: Boolean = false
    var diAntrian: Boolean = false
    var userDiAntrian: Boolean = false
    var dataUser: Antrian? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This codelab uses View Binding
        // See: https://developer.android.com/topic/libraries/view-binding
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.getRoot())
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        if (!diAntrian){
            hapusAntrian()
        }
        // Initialize Firebase Auth and check if the user is signed in
        mFirebaseAuth = FirebaseAuth.getInstance()
        if (mFirebaseAuth!!.currentUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mSignInClient = GoogleSignIn.getClient(this, gso)

        // Initialize Realtime Database

        Log.i("info", "activity oncreate")

        // Disable the send button when there's no text in the input field
        // See MyButtonObserver.java for details

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in.
        // TODO: Add code to check if user is signed in.
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        adaAntrian = false
        hapusAntrian()
        Log.i("info", "activity ondestroy")
    }

    fun createQueue(data: Antrian){
        dataAntrian = data
        nodeRef.child(data.nama).setValue(data).addOnCompleteListener {
            Log.i("Main", "berhasil menambahkan ke database")
            adaAntrian = true
        }.addOnFailureListener {
            Log.i("Main", "Gagal menambahkan ke database")
        }
    }

    fun hapusAntrian(){
        if (adaAntrian){
            adaAntrian = false
            dataUser = null
            dataAntrian?.nama?.let { nodeRef.child(it).removeValue() }
        }
    }

    fun signOut() {
        mFirebaseAuth!!.signOut()
        mSignInClient!!.signOut()
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (diHome){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Keluar dan Hapus Antrian ?")
            builder.setCancelable(false)
            builder.setPositiveButton("Ya", DialogInterface.OnClickListener { _, i ->
                hapusAntrian()
                Navigation.findNavController(mBinding.root).popBackStack()
            })
            builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
            val alertDialog = builder.create()
            alertDialog.show()
        }
        else{
            super.onBackPressed()
        }
    }
}