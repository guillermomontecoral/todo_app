package com.mntcrl.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mntcrl.todoapp.R
import com.mntcrl.todoapp.Task
import com.mntcrl.todoapp.TaskCategory
import com.mntcrl.todoapp.viewholder.ViewHolderCategorias
import com.mntcrl.todoapp.viewholder.ViewHolderTareas

class AdaptadorTareas (var tareas:List<Task>, private val tareaSeleccionada:(Int) -> Unit) : RecyclerView.Adapter<ViewHolderTareas>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTareas {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return ViewHolderTareas(view)
    }

    override fun getItemCount(): Int = tareas.size

    override fun onBindViewHolder(holder: ViewHolderTareas, position: Int) {
        holder.render(tareas[position])
        holder.itemView.setOnClickListener { tareaSeleccionada(position) }

    }
}