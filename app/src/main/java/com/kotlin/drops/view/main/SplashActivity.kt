package com.kotlin.drops.view.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.kotlin.drops.R
import com.kotlin.drops.databinding.ActivitySplashBinding
import com.kotlin.drops.reposetories.ApiServiceRepository
import com.kotlin.drops.reposetories.SHARED_PREF_FILE
import com.kotlin.drops.view.identity.LoginActivity
import com.kotlin.drops.view.identity.SignUpConfrmationActivity

const val SHARED_PREF_FILE = " login state"
const val STATE = "state"
const val USER_ID = "userId"
private const val TAG = "SplashActivity"

class SplashActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashBinding
//  val  sharedPref= getSharedPreferences("Auth", Context.MODE_PRIVATE)
//   val  sharedPrefEditor= sharedPref.edit()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)

        //hide action bar
        supportActionBar?.hide()

        //hiding status bar

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        ApiServiceRepository.init(this)

        // for the status bar to gone in the splash and set the fullscreen in android R
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }


        //set time for splash
        val intent = Intent(this, MainActivity::class.java)

        object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                Log.d(TAG, "finish")
                startActivity(intent)
                finish()
            }
        }.start()

//        sharedPref= getSharedPreferences("Auth", Context.MODE_PRIVATE)
//        sharedPrefEditor= sharedPref.edit()

//        if (sharedPref.getBoolean("status", false)) {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        } else {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//
//        }

    }
}