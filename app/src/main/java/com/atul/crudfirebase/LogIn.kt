package com.atul.crudfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogIn : AppCompatActivity() {
    lateinit var fAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        var actionBar: ActionBar
        actionBar = supportActionBar!!
        actionBar.setTitle("Log IN")
        var lgin = findViewById<TextView>(R.id.lgin)
        lgin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        fAuth = FirebaseAuth.getInstance()
        checkUser()
    }

    private fun checkUser() {
        val firebaseUser = fAuth.currentUser
        if (firebaseUser != null){
            startActivity(Intent(this,Profile::class.java))
            finish()
        }
    }
}