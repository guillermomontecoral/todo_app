package com.mntcrl.todoapp.viewholder

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mntcrl.todoapp.R
import com.mntcrl.todoapp.Task
import com.mntcrl.todoapp.TaskCategory

class ViewHolderTareas(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTareas: TextView = view.findViewById(R.id.tvTituloTarea)
    private val tvDescripcionTarea: TextView = view.findViewById(R.id.tvDescripcionTarea)
    private val cbTareas: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task) {
        if(task.isSelected){
            tvTareas.paintFlags = tvTareas.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            tvDescripcionTarea.paintFlags = tvTareas.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTareas.paintFlags = tvTareas.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            tvDescripcionTarea.paintFlags = tvTareas.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

        }

        tvTareas.text = task.name
        tvDescripcionTarea.text = task.descripcion
        cbTareas.isChecked = task.isSelected

        val color = when(task.category){
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Other -> R.color.todo_other_category
            TaskCategory.Personal -> R.color.todo_personal_category
        }
        cbTareas.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTareas.context, color)
        )
    }
}