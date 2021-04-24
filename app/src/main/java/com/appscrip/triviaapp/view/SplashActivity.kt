package com.appscrip.triviaapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.appscrip.triviaapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        doSplash()
    }

    private fun doSplash(){
        Handler().postDelayed(
            {
                val moveTo = Intent(this, UserInfoSaveActivity::class.java)
                startActivity(moveTo)
                finishAffinity()

            }, 2500)
    }
}