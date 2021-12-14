package com.kotlin.drops.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.kotlin.drops.R
import com.kotlin.drops.view.identity.LoginActivity

private const val TAG = "SplashActivity"
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        supportActionBar?.hide()

// set time for splash
        val intent= Intent(this, LoginActivity::class.java)
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