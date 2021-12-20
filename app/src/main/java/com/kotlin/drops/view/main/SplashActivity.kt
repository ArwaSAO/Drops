package com.kotlin.drops.view.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.kotlin.drops.R
import com.kotlin.drops.view.identity.LoginActivity

 lateinit var sharedPref: SharedPreferences
 lateinit var sharedPrefEditor: SharedPreferences.Editor
private const val TAG = "SplashActivity"
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        sharedPref= getSharedPreferences("Auth", Context.MODE_PRIVATE)
        sharedPrefEditor= sharedPref.edit()

        if (sharedPref.getBoolean("status", false)){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
// set time for splash
//        val intent= Intent(this, LoginActivity::class.java)


        object : CountDownTimer(2000,1000){
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                Log.d(TAG, "finish")
                startActivity(intent)
                finish()
            }
        }.start()

    }
}