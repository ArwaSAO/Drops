package com.kotlin.drops.view.identity

import android.app.ProgressDialog
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
import com.google.firebase.auth.FirebaseUser
import com.kotlin.drops.R
import com.kotlin.drops.view.main.MainActivity
import com.kotlin.drops.view.main.USER_ID


private const val TAG = "RejisterActivity"
class RejisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rejister)

        var progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading...")
        progressDialog.setCancelable(false)

        supportActionBar?.hide()

        val emailAddress: EditText = findViewById(R.id.rejester_Emailadress)
        val password: EditText = findViewById(R.id.password_register)
        val rejesterButton: Button = findViewById(R.id.rejister_button)
        val loginTextView: TextView = findViewById(R.id.login_textView4)



        loginTextView.setOnClickListener() {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        rejesterButton.setOnClickListener() {
            val email: String = emailAddress.text.toString()
            val password: String = password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(){
                            task ->
                        if(task.isSuccessful){
                            val fireBaseUser: FirebaseUser = task.result!!.user!!

                            Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show()


                            // assign shared preferences
                            sharedPreferEdit = sharedPreferences.edit()
                            sharedPreferEdit.putBoolean(com.kotlin.drops.view.main.STATE, true)
                            sharedPreferEdit.putString(
                                USER_ID,
                                FirebaseAuth.getInstance().currentUser!!.uid
                            )
                            sharedPreferEdit.commit()


                            // Navigate to main activity

                            val intent = Intent(this, SignUpConfrmationActivity::class.java)
                            intent.putExtra("UserId", fireBaseUser.uid)
                            intent.putExtra("Email",fireBaseUser.email)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}