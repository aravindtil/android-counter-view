package com.aravind.androidcounterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aravind.androidcounterview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            tcv1.setText(textToSet = "$987.23")
            tcv1.updateView(animate = true)

            tcv2.setText(textToSet = "4.8")
            tcv2.updateView(animate = true)

            tcv3.setText(textToSet = "₹1,345")
            tcv3.updateView(animate = true)
        }
    }
}