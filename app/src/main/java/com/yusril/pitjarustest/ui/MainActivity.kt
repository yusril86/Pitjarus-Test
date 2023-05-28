package com.yusril.pitjarustest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yusril.pitjarustest.ui.store.StoreActivity
import com.yusril.pitjarustest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val window = this.window
        val decorView = window.decorView

        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        binding.btnKunjungan.setOnClickListener {
            startActivity(Intent(this, StoreActivity::class.java))
        }
    }
}