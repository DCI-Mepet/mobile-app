package com.bangkit.ch2_ps178_android.view.login

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
import com.bangkit.ch2_ps178_android.databinding.ActivityLoginBinding
import com.bangkit.ch2_ps178_android.view.detail.DetailActivity
import com.bangkit.ch2_ps178_android.view.main.MainActivity
import com.bangkit.ch2_ps178_android.view.signup.SignupActivity
import com.bangkit.ch2_ps178_android.view.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var moveToSignup: TextView
    private lateinit var progressDialog: ProgressDialog


    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editEmail = binding.editLoginEmail
        editPassword = binding.editLoginPassword
        btnLogin = binding.btnLogin
        moveToSignup = binding.tvLoginToSignup

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Proses validasi.")
        progressDialog.setMessage("Tunggu sebentar, ya!")


        //Ketika tmbol login di klik dan melakukan validasi
        btnLogin.setOnClickListener {
            if (editEmail.text.isNotEmpty() && editPassword.text.isNotEmpty()) {
                loginProcess()
            } else {
                BaseModel.swal(this, "Email dan password diisi dulu, ya!")
            }
        }

        moveToSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        setupView()
    }

    private fun loginProcess() {
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        progressDialog.show()
        direct_main( "1")

    }
    fun direct_main( param : String ){
        //Ini ngirim data dengan anggapan data ke detail diambil dari link by id
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("param", param)
        startActivity(intent)
    }

    private fun setupView() {
        val toolbar: Toolbar = findViewById(R.id.tb_login_back)
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