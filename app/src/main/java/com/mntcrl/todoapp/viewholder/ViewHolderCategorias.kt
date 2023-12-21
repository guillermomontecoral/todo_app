package com.mntcrl.todoapp.viewholder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mntcrl.todoapp.R
import com.mntcrl.todoapp.TaskCategory

class ViewHolderCategorias(view : View) : RecyclerView.ViewHolder(view){

    private val tvNombreCategoria:TextView = view.findViewById(R.id.tvNombreCategoria)
    private val vDivider:View = view.findViewById(R.id.vDivider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)


    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit){

        val color = if(taskCategory.isSelected){
            R.color.todo_background_card
        }
        else{
            R.color.todo_background_disabled
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        when(taskCategory){
            TaskCategory.Business -> {
                tvNombreCategoria.text = "Negocios"
                vDivider.setBackgroundColor(
                    ContextCompat.getColor(vDivider.context, R.color.todo_business_category)
                )
            }
            TaskCategory.Other -> {
                tvNombreCategoria.text = "Otros"
                vDivider.setBackgroundColor(
                    ContextCompat.getColor(vDivider.context, R.color.todo_other_category)
                )
            }
            TaskCategory.Personal -> {
                tvNombreCategoria.text = "Personal"
                vDivider.setBackgroundColor(
                    ContextCompat.getColor(vDivider.context, R.color.todo_personal_category)
                )
            }
        }
    }
}