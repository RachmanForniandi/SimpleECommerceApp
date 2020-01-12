package com.example.simpleecommerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.simpleecommerceapp.ui.login.LoginActivity
import org.jetbrains.anko.startActivity

class SplashscreenActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long =3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity<LoginActivity>()
        },SPLASH_TIME_OUT)
    }
}
