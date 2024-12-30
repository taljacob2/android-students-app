package com.example.androidstudentsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUi()
    }

    private fun setupUi() {
        button = findViewById(R.id.buttonMainStopActivity)
        button!!.setOnClickListener { finish() }
    }

    override fun onStop() {
        super.onStop()

        Log.d(MainActivity::class.java.name, "activity stopped")
    }
}