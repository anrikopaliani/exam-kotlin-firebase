package com.example.exam

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPassword : AppCompatActivity() {
    private val firebaseAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val resetButton = findViewById<Button>(R.id.resetButton)
        val emailText = findViewById<EditText>(R.id.resetEmail)
        val goBackToLogin = findViewById<ImageView>(R.id.gobackToLogin)

        goBackToLogin.setOnClickListener {
            goToLogin()
        }

        resetButton.setOnClickListener {
            val email = emailText.text.toString()

            if(email.isEmpty()) {
                return@setOnClickListener
            }

            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{ task ->
                if(task.isSuccessful) {
                 Toast.makeText(this, "check your email", Toast.LENGTH_SHORT).show()
                    goToLogin()
                } else {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun goToLogin(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}