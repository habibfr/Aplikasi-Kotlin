package com.kt.kedua

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter(private val context: Context, private val textViewEmpty: TextView) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val taskList = ArrayList<Task>()

    interface OnItemClickListener {
        fun onUpdateClick(task: Task)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        val viewHolder = ViewHolder(view, textViewEmpty, this)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun addTask(task: Task) {
        taskList.add(task)
        notifyItemInserted(taskList.size - 1)
    }

    fun updateTask(position: Int, newTask: Task) {
        taskList[position] = newTask
        notifyItemChanged(position)
    }

    fun getTask(position: Int): Task {
        return taskList[position]
    }

    inner class ViewHolder(
        itemView: View,
        private val textViewEmpty: TextView,
        private val taskAdapter: TaskAdapter
    ) : RecyclerView.ViewHolder(itemView), View.OnLongClickListener {

        private val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        private val textViewDesc = itemView.findViewById<TextView>(R.id.textViewDescription)
        private val buttonUpdate = itemView.findViewById<ImageButton>(R.id.buttonUpdate)
        private val buttonDelete = itemView.findViewById<ImageButton>(R.id.buttonDelete)


        init {
            buttonUpdate.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selectedTask = taskAdapter.getTask(position)
                    showTaskDialog(selectedTask, position)
                }
            }

            buttonDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    taskList.removeAt(position)
                    notifyItemRemoved(position)
                    if (taskList.isEmpty()) {
                        textViewEmpty.visibility = View.VISIBLE
                    }
                }
            }
        }

        private fun showTaskDialog(task: Task, position: Int) {
            val dialogBuilder = AlertDialog.Builder(context)
            val inflater = LayoutInflater.from(context)
            val dialogView = inflater.inflate(R.layout.dialog_update_task, null)
            val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)
            val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDescription)

            editTextTitle.setText(task.title)
            editTextDescription.setText(task.description)

            dialogBuilder.setView(dialogView)
                .setTitle("Update Task")
                .setPositiveButton("Update") { dialog, which ->
                    val newTitle = editTextTitle.text.toString()
                    val newDescription = editTextDescription.text.toString()
                    val newTask = Task(newTitle, newDescription)
                    taskAdapter.updateTask(position, newTask)
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.cancel()
                }
                .create()
                .show()
        }

        fun bind(task: Task) {
            textViewTitle.text = task.title
            textViewDesc.text = task.description
        }

        override fun onLongClick(p0: View?): Boolean {
            TODO("Not yet implemented")
        }
    }
}