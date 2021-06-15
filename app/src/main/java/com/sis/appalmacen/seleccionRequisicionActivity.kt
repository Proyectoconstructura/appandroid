package com.sis.appalmacen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class seleccionRequisicionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_requisicion)

        val botonSalir = findViewById<Button>(R.id.btnSalir)
        val botonAtras= findViewById<Button>(R.id.btnAtras)
        val botonVerLista= findViewById<Button>(R.id.btnAtras)

        botonSalir.setOnClickListener {
            finish()
        }



    }
}