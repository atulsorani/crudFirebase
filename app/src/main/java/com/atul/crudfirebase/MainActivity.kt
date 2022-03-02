package com.atul.crudfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var actionBar:ActionBar

        actionBar = supportActionBar!!
        actionBar.setTitle("Sign Up")
        var name = findViewById<EditText>(R.id.name)
        var number = findViewById<EditText>(R.id.number)
        var pass = findViewById<EditText>(R.id.pass)
        var cpass = findViewById<EditText>(R.id.cpass)
        var signup = findViewById<Button>(R.id.signup)
        var probar = findViewById<ProgressBar>(R.id.process)
        var fAuth = FirebaseAuth.getInstance()
        var sgin = findViewById<TextView>(R.id.sgin)

        sgin.setOnClickListener {
            startActivity(Intent(this,LogIn::class.java))
            finish()
        }

        signup.setOnClickListener {
            var semail = name.text.toString()
            var snumber = number.text.toString()
            var spass = pass.text.toString()
            var scpass = cpass.text.toString()

            if (semail.isEmpty() && snumber.isEmpty() && spass.isEmpty() && scpass.isEmpty()){
                Toast.makeText(this, "Fields Empty", Toast.LENGTH_SHORT).show()
            }else{
                if (spass.equals(scpass)){
                    probar.isVisible = true
                    fAuth.createUserWithEmailAndPassword(semail,spass).addOnSuccessListener {
                        probar.isVisible = false
                        val firebaseUser = fAuth.currentUser
                        val email = firebaseUser!!.email
                        Toast.makeText(this, "Accout Created with \n $email", Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this, Profile::class.java))
                        finish()
                    }.addOnFailureListener {e->
                        probar.isVisible = false
                        Toast.makeText(this, "Sign up Failed due to ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Password Does not match", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}