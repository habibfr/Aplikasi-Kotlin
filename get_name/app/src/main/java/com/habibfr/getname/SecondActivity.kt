package com.habibfr.getname

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val txtResultName = findViewById<TextView>(R.id.txtResultName)

        val enteredName = intent.getStringExtra("USER")
        val message = "Hello $enteredName, welcome to the world jungle."
        txtResultName.text = message

    }
}