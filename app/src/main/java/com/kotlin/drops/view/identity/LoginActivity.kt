package com.kotlin.drops.view.identity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.wifi.rtt.CivicLocationKeys.STATE
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
import com.kotlin.drops.view.main.*

      lateinit var  sharedPreferences : SharedPreferences
      lateinit var sharedPreferEdit: SharedPreferences.Editor

private lateinit var progressDialog: ProgressDialog
private const val TAG = "LoginActivity"
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading...")
        progressDialog.setCancelable(false)


        supportActionBar?.hide()



        val emailAddress: EditText = findViewById(R.id.editTextTextEmailAddress2)
        val password: EditText = findViewById(R.id.editTextNumberPassword2)
        val loginButton: Button = findViewById(R.id.login_button)
        val rejesterTextView: TextView = findViewById(R.id.rejester_textView2)


        rejesterTextView.setOnClickListener {
            startActivity(Intent(this, RejisterActivity::class.java))
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

                            sharedPreferEdit= sharedPreferences.edit()
                            sharedPreferEdit.putBoolean(com.kotlin.drops.view.main.STATE, true)
                            sharedPreferEdit.putString(USER_ID, FirebaseAuth.getInstance().currentUser!!.uid)
                            sharedPreferEdit.commit()

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