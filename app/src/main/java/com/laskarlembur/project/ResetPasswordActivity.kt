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
import com.google.firebase.ktx.Firebase

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var resetbutton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var contextemail: EditText
    private lateinit var contextpass: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        auth = FirebaseAuth.getInstance()

        contextemail = findViewById(R.id.etEmail)

        resetbutton = findViewById(R.id.btnReset)
        resetbutton.setOnClickListener {
            val email = contextemail.text.toString().trim()
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

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Cek email untuk reset password!", Toast.LENGTH_SHORT).show()
                    Intent(this@ResetPasswordActivity, LoginActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}