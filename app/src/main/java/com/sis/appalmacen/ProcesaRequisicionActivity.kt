package com.sis.appalmacen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProcesaRequisicionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procesa_requisicion)

        val botonSalir = findViewById<Button>(R.id.btnSalir)
        val botonMenu= findViewById<Button>(R.id.btnMenu)

        botonSalir.setOnClickListener {
            finish()
        }

        botonMenu.setOnClickListener{
            val intento1 = Intent(this, activityAlmacenista::class.java)
            startActivity(intento1)
        }
    }
}