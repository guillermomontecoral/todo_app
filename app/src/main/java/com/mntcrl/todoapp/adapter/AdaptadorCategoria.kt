package com.mntcrl.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mntcrl.todoapp.R
import com.mntcrl.todoapp.TaskCategory
import com.mntcrl.todoapp.viewholder.ViewHolderCategorias

class AdaptadorCategoria(private val categorias:List<TaskCategory>, private val onItemSelected:(Int) -> Unit) : RecyclerView.Adapter<ViewHolderCategorias>() {

    //Crea una vista visual
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategorias {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return ViewHolderCategorias(view)
    }

    //Siempre va a mostrar el tamaño del listado que retorne
    override fun getItemCount(): Int = categorias.size


    override fun onBindViewHolder(holder: ViewHolderCategorias, position: Int) {
        holder.render(categorias[position], onItemSelected)
    }
}