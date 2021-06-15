package com.sis.appalmacen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class reportesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportes)

        val botonSalir = findViewById<Button>(R.id.btnSalir)
        val botonMenu = findViewById<Button>(R.id.btnMenu)
        val RI=findViewById<Button>(R.id.RI)


        botonSalir.setOnClickListener {
            finish()
        }


        RI.setOnClickListener{
            val intento3 = Intent(this, RequisicionInsumos::class.java)
            startActivity(intento3)
        }


    }
}