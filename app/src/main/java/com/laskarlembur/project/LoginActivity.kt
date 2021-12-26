package com.laskarlembur.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var loginbutton: Button
    private lateinit var contextemail: EditText
    private lateinit var contextpass: EditText
    private lateinit var resetpw : TextView
    private lateinit var registerbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        resetpw = findViewById(R.id.btnForgotPassword)
        resetpw.setOnClickListener {
            Intent(this@LoginActivity, ResetPasswordActivity::class.java).also {
                startActivity(it)
            }
        }

        contextemail = findViewById(R.id.etEmail)
        contextpass = findViewById(R.id.etPassword)

        loginbutton = findViewById(R.id.btnLogin)
        loginbutton.setOnClickListener {
            val email = contextemail.text.toString().trim()
            val password = contextpass.text.toString().trim()

            if (email.isEmpty()) {
                contextemail.error = ("Email Harus diisi!")
                contextemail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                contextemail.error = ("Email tidak Valid!")
                contextemail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                contextpass.error = "Password Kurang dari 6 Karakter!"
                contextpass.requestFocus()
                return@setOnClickListener
            }

            LoginUser(email, password)

        }

        registerbutton = findViewById(R.id.btnRegister)
        registerbutton.setOnClickListener {
            Intent(this@LoginActivity, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun LoginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Intent(this@LoginActivity, Home2Activity::class.java).also { intent ->
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

        override fun onStart() {
            super.onStart()
            if (auth.currentUser != null) {
                Intent(this@LoginActivity, Home2Activity::class.java).also { intent ->
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }
    }