package com.sis.appalmacen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class jefeObraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jefe_obra)

        val botonCreaLista = findViewById<Button>(R.id.btnCreaListaInsumos)
        val botonEditaLista = findViewById<Button>(R.id.btnModificarListaInsumos)
        botonCreaLista.setOnClickListener{
            intent= Intent(this, listainsumos::class.java)
            startActivity(intent)
        }





    }
}