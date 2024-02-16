package com.piscokz.Pengolah_rumus_compose//package com.piscokz.Pengolah_rumus_compose
//
//import android.content.Intent
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.view.WindowManager
//import androidx.appcompat.app.AppCompatActivity
//
//class SplashScreen : AppCompatActivity() {
//
//    lateinit var handler: android.os.Handler
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//            supportActionBar?.hide()
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//
//            handler = Handler(Looper.getMainLooper())
//            handler.postDelayed({
//                val intent = Intent(this@SplashScreen, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }, 2500)
//        }
//    }
