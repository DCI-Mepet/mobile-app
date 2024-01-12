package com.bangkit.ch2_ps178_android.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.model.BaseModel

import com.bangkit.ch2_ps178_android.view.history.HistoryFragment
import com.bangkit.ch2_ps178_android.view.home.HomeFragment
import com.bangkit.ch2_ps178_android.view.profile.ProfileFragment
import com.bangkit.ch2_ps178_android.view.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(), ProfileFragment.LogoutListener {


    lateinit var bottomNav: BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Gantilah dengan layout utama Anda (biasanya R.layout.activity_main)


        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        supportActionBar?.hide()
        bottomNav = findViewById(R.id.bottomNav)




        setupFragments()
        setupBottomNavigation()

//        direct_welcome()
    }



    fun direct_welcome(){
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }
    private fun setupFragments() {
        loadFragment(HomeFragment())
    }

    private fun setupBottomNavigation() {
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.sensor -> {

                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    override fun onLogout() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }
}