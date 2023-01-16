package com.example.exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.exam.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import org.checkerframework.common.subtyping.qual.Bottom
import javax.annotation.meta.When

class ProfileActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_profile)
        //setContentView(binding.root)


       val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
      //  val navController = findNavController(R.id.fragmentContainerView)
        replaceFragment(ProfileFragment())
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.profileFragment -> replaceFragment(ProfileFragment())
                R.id.notificationsFragment -> replaceFragment(NotificationsFragment())
                R.id.settingsFragment -> replaceFragment(settingsFragment())

                else -> {

                }
            }

            true
        }

     //   bottomNavigationView.setupWithNavController(navController)

      //  val appBarConfiguration = AppBarConfiguration(setOf(R.id.profileFragment,R.id.notificationsFragment, R.id.settingsFragment))
      //  setupActionBarWithNavController(navController, appBarConfiguration)
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}