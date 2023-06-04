package com.habibfr.getname

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtName = findViewById<TextView>(R.id.txtName)
        val editName = findViewById<EditText>(R.id.editName)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        btnCheck.visibility = INVISIBLE

        var enteredName = ""
        btnSubmit.setOnClickListener {
            enteredName = editName.text.toString()
            val message = "Welcome $enteredName"

            if (enteredName.isNotEmpty()) {
                txtName.text = message
                editName.text.clear()
                btnCheck.visibility = VISIBLE
            } else {
                Toast.makeText(applicationContext, "please fill the input!", Toast.LENGTH_LONG)
                    .show()
            }
        }

        btnCheck.setOnClickListener {
            val intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra("USER", enteredName)
            startActivity(intent)
        }

    }
}