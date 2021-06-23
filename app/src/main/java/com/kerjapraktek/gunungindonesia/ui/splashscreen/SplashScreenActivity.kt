package com.kerjapraktek.gunungindonesia.ui.splashscreen

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ActivitySplashScreenBinding
import com.kerjapraktek.gunungindonesia.ui.main.MainActivity

class SplashScreenActivity : Activity() {
    companion object{
        const val TIME:Long=5000
    }
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        Glide.with(this)
            .load(R.drawable.common_google_signin_btn_icon_dark)
            .into(binding.icLogo)
        Handler(Looper.getMainLooper()).postDelayed({ moveActivity() },TIME)
        setContentView(binding.root)
    }
    private fun moveActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}