package com.mntcrl.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mntcrl.todoapp.MainActivity.Companion.NOMBRE_USUARIO
import com.mntcrl.todoapp.adapter.AdaptadorCategoria
import com.mntcrl.todoapp.adapter.AdaptadorTareas

class TodoActivity : AppCompatActivity() {

    private lateinit var tvTituloBienvenida: TextView
    private lateinit var rvCategorias: RecyclerView
    private lateinit var rvTareas: RecyclerView
    private lateinit var adaptadorCategoria: AdaptadorCategoria
    private lateinit var adaptadorTareas: AdaptadorTareas
    private lateinit var fabAgregarTarea: FloatingActionButton

    //Lo que reciba en esta lista es lo que va a mostrar en la pantalla en la parte de categorias
    private val categorias = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Other
    )

    private val tareas = mutableListOf(
        Task("Prueba Negocios", "Descripción de prueba para la categoria negocios", TaskCategory.Business),
        Task("Prueba Personal", "Descripción de prueba para la categoria personal", TaskCategory.Personal),
        Task("Prueba Otros", "Descripción de prueba para la categoria otros", TaskCategory.Other)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAgregarTarea.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val btnAgregarTarea: Button = dialog.findViewById(R.id.btnAgregarTarea)
        val etTituloTarea: EditText = dialog.findViewById(R.id.etTituloTarea)
        val etDescripcionTarea: EditText = dialog.findViewById(R.id.etDescripcionTarea)
        val rgCategorias: RadioGroup = dialog.findViewById(R.id.rgCategorias)

        btnAgregarTarea.setOnClickListener {
            val tituloTarea = etTituloTarea.text.toString()
            val descripcionTarea = etDescripcionTarea.text.toString()
            if (tituloTarea.isNotEmpty() && descripcionTarea.isNotEmpty()) {
                val selectedId = rgCategorias.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategorias.findViewById(selectedId)
                val categoriaActual: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_negocios) -> TaskCategory.Business
                    getString(R.string.todo_personal) -> TaskCategory.Personal
                    else -> TaskCategory.Other
                }

                tareas.add(Task(tituloTarea, descripcionTarea, categoriaActual))
                updateTask()
                dialog.hide()
            }

        }
        dialog.show()
    }

    private fun initUI() {
        tvTituloBienvenida.text =
            getString(R.string.bienvenido) + " " + intent.extras?.getString(NOMBRE_USUARIO)

        adaptadorCategoria = AdaptadorCategoria(categorias){ posicion -> updateCategories(posicion) }
        //LayoutManager se encarga de que la vista sea horizontal o vertical (scroll)
        rvCategorias.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategorias.adapter = adaptadorCategoria

        adaptadorTareas = AdaptadorTareas(tareas) { posicion -> onItemSelected(posicion) }
        rvTareas.layoutManager = LinearLayoutManager(this)
        rvTareas.adapter = adaptadorTareas
    }

    private fun onItemSelected(posicion:Int){
        tareas[posicion].isSelected = !tareas[posicion].isSelected
        updateTask()
    }

    private fun initComponent() {
        tvTituloBienvenida = findViewById(R.id.tvTituloBienvenida)
        rvCategorias = findViewById(R.id.rvCategorias)
        rvTareas = findViewById(R.id.rvTareas)
        fabAgregarTarea = findViewById(R.id.fabAgregarTarea)

    }

    //Avisa al adaptador pora hora si hay alguna tarea nueva
    private fun updateTask() {
        var categoriaSeleccionadas:List<TaskCategory> = categorias.filter { it.isSelected }
        val newTasks = tareas.filter { categoriaSeleccionadas.contains(it.category) }
        adaptadorTareas.tareas = newTasks
        adaptadorTareas.notifyDataSetChanged()
    }


    private fun updateCategories(position: Int){
        categorias[position].isSelected = !categorias[position].isSelected
        adaptadorCategoria.notifyItemChanged(position)
        updateTask()
    }
}