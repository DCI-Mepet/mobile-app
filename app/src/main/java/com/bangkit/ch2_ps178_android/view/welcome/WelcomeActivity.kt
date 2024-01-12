package com.bangkit.ch2_ps178_android.view.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.databinding.ActivityWelcomeBinding
import com.bangkit.ch2_ps178_android.view.login.LoginActivity
import com.bangkit.ch2_ps178_android.view.signup.SignupActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


//        BaseModel.swal(this, "Coba")

        setupAction()
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}