package com.mntcrl.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {

    companion object{
        const val NOMBRE_USUARIO = "NOMBRE_USUARIO"
    }

    private lateinit var etNombreUsuario: AppCompatEditText
    private lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponenet()
        initListener()
    }

    private fun initComponenet() {
        etNombreUsuario = findViewById<AppCompatEditText>(R.id.etNombreUsuario)
        btnCalcular = findViewById(R.id.btnContinuar)
    }
    private fun initListener() {
        btnCalcular.setOnClickListener {
            mostrarNombre(etNombreUsuario.text.toString())
        }
    }

    private fun mostrarNombre(nombre: String) {
        if(nombre.isNotEmpty()){
            val intent = Intent(this, TodoActivity::class.java)
            intent.putExtra(NOMBRE_USUARIO, nombre)
            startActivity(intent)
        }
    }

}