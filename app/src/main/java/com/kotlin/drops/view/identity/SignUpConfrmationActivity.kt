package com.kotlin.drops.view.identity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.kotlin.drops.R
import com.kotlin.drops.view.main.MainActivity

class SignUpConfrmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_confrmation)

        supportActionBar?.hide()


        val userIdTextView: TextView = findViewById(R.id.user_Id_textview)
        val emailAddressTextView: TextView = findViewById(R.id.email_adress_textview)
        val satartDonataitons: Button = findViewById(R.id.start_button)

        val userId = intent.getStringExtra("UserId")
        val emailAddress = intent.getStringExtra("Email")

        userIdTextView.text = " User ID: " + userId
        emailAddressTextView.text = " EmailAddress: " + emailAddress

        satartDonataitons.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}