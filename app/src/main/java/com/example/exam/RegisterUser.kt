package com.example.exam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exam.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class RegisterUser : AppCompatActivity() {
    private val firebaseAuth = Firebase.auth

    private val db = FirebaseDatabase.getInstance().getReference("userInfo")
    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        var registerUser = findViewById<Button>(R.id.registerUser)
        var editTextFullName = findViewById<EditText>(R.id.fullName)
        var editTextAge = findViewById<EditText>(R.id.age)
        var editTextPassword = findViewById<EditText>(R.id.passwordRegister)
        var editTextEmail = findViewById<EditText>(R.id.email)
        var arrowBackLogin = findViewById<ImageView>(R.id.arrowBack)


        arrowBackLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        registerUser.setOnClickListener {
            val email = editTextEmail.text.toString()
            val age = editTextAge.text.toString()
            val fullName = editTextFullName.text.toString()
            val password = editTextPassword.text.toString()


            if(email.isEmpty() || age.isEmpty() || fullName.isEmpty() || password.isEmpty()) {
                return@setOnClickListener
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {task ->
                if(task.isSuccessful) {
                    database = FirebaseDatabase.getInstance().getReference("Users")
                    val User = User(fullName, age, password, email)
                    database.child(fullName).setValue(User).addOnSuccessListener {
                        Toast.makeText(this, "success! you can now login to your account", Toast.LENGTH_SHORT).show()
                    }
                    goToLogin()
                } else {
                    Toast.makeText(this, "something is wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }


    private fun goToLogin(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}