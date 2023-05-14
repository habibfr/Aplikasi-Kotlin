package com.kt.kedua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDesc: EditText
    private lateinit var buttonAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var textViewEmpty: TextView

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextDesc = findViewById(R.id.editTextDescription)
        buttonAdd = findViewById(R.id.buttonAdd)
        recyclerView = findViewById(R.id.recyclerView)
        textViewEmpty = findViewById(R.id.textViewEmpty)

        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(this, textViewEmpty)
        recyclerView.adapter = taskAdapter

        buttonAdd.setOnClickListener {
            val title = editTextTitle.text.toString()
            val desc = editTextDesc.text.toString()
            if (title.isNotEmpty() && desc.isNotEmpty()) {
                val task = Task(title, desc)
                taskAdapter.addTask(task)
                editTextTitle.text.clear()
                editTextDesc.text.clear()
                textViewEmpty.visibility = View.GONE
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



