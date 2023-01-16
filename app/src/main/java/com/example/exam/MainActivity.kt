package com.example.exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val register = findViewById(R.id.register) as TextView
        val loginButton = findViewById(R.id.login) as Button
        val emailEditText = findViewById<EditText>(R.id.email)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val forgotPasswordButton = findViewById<TextView>(R.id.forgotPassword)

        register.setOnClickListener {
            startActivity(Intent(this, RegisterUser::class.java))
            finish()
        }

        forgotPasswordButton.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
            finish()
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
                if(task.isSuccessful) {
                    startActivity(Intent(this, ProfileActivity::class.java).putExtra("email", email))
                    finish()
                } else {
                    Toast.makeText(this, "something's wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}