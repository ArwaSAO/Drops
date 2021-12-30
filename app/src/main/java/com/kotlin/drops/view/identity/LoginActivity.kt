package com.kotlin.drops.view.identity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kotlin.drops.R
import com.kotlin.drops.view.main.MainActivity
import com.kotlin.drops.view.main.sharedPref
import com.kotlin.drops.view.main.sharedPrefEditor

private const val TAG = "LoginActivity"
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

//        sharedPref = getSharedPreferences("Auth", Context.MODE_PRIVATE)
//        sharedPrefEditor = sharedPref.edit()

        val emailAddress: EditText = findViewById(R.id.editTextTextEmailAddress2)
        val password: EditText = findViewById(R.id.editTextNumberPassword2)
        val loginButton: Button = findViewById(R.id.login_button)
        val rejesterTextView: TextView = findViewById(R.id.rejester_textView2)


        rejesterTextView.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        loginButton.setOnClickListener {
            val email: String = emailAddress.text.toString()
            val password: String = password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "User Logged in Successfully", Toast.LENGTH_SHORT)
                                .show()

                            // Navigate to main activity

                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("UserID", FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("Email", FirebaseAuth.getInstance().currentUser!!.email)
                            startActivity(intent)
                            finish()
                        } else{
                            Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }


    }
}