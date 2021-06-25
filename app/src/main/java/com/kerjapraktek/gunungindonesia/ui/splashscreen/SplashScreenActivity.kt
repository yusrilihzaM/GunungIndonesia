package com.kerjapraktek.gunungindonesia.ui.splashscreen

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ActivitySplashScreenBinding
import com.kerjapraktek.gunungindonesia.ui.main.MainActivity
import com.kerjapraktek.gunungindonesia.viewmodel.InfoViewModel

class SplashScreenActivity : AppCompatActivity() {
    companion object{
        const val TIME:Long=8000
    }
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var viewModel: InfoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(InfoViewModel::class.java)

        viewModel.setInfoDetail()
        viewModel.getAppInfo().observe(this,{data->
            //menampilkan logo
            Glide.with(this)
                .load(data[0].gambarAplikasi)
                .into(binding.icLogo)
            //durasi splash screen
            Handler(Looper.getMainLooper()).postDelayed({ moveActivity() },TIME)
            //menampilkan judul app
            binding.tvTitleSplash.text=data[0].namaAplikasi.toString()
        })
    }
    private fun moveActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}