package com.isa.subscriber_manager

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        editTextUsername = findViewById(R.id.edit_text_admin_login)
        editTextPassword = findViewById(R.id.edit_text_password_admin_login)
        buttonLogin = findViewById(R.id.button_login_enter_admin)

        buttonLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val username = editTextUsername.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            return
        }

        if (username == "admin" && password == "admin")
        {
            val intent = Intent(this, MainAdminMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }
}
