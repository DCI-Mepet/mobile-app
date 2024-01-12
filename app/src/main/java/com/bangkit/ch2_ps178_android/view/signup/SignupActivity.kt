package com.bangkit.ch2_ps178_android.view.signup

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.databinding.ActivitySignupBinding
import com.bangkit.ch2_ps178_android.view.login.LoginActivity
import com.bangkit.ch2_ps178_android.view.main.MainActivity
import com.bangkit.ch2_ps178_android.view.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var btnSignup: Button
    lateinit var moveToLogin: TextView
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editName = binding.editSignupName
        editEmail = binding.editSignupEmail
        editPassword = binding.editSignupPassword
        btnSignup = binding.btnSignup
        moveToLogin = binding.tvSignupToLogin

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Sedang daftarin kamu, iya kamu.")
        progressDialog.setMessage("Tunggu sebentar, ya!")

        btnSignup.setOnClickListener {
            if (editName.text.isNotEmpty() && editEmail.text.isNotEmpty() && editPassword.text.isNotEmpty()) {
                signupProcess()
            } else {
                BaseModel.swal(this, "Lengkapin dulu dong, pren!")
            }
        }

        moveToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


        setupView()
    }

    fun signupProcess() {
        val name = editName.text.toString()
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        BaseModel.swal(this, "Proses validasi daftar")

    }


    private fun setupView() {
        val toolbar: Toolbar = findViewById(R.id.tb_signup_back)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}