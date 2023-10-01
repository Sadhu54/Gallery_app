package com.thoughtctl.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.thoughtctl.R
import com.thoughtctl.databinding.ActivitySplashBinding
import com.thoughtctl.presentation.gallery.MainActivity

class SplashActivity : AppCompatActivity() {
    lateinit var mBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivitySplashBinding.inflate(layoutInflater)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },2000)
        setContentView(mBinding.root)
    }
}