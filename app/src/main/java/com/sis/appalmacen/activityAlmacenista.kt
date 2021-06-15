package com.sis.appalmacen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class activityAlmacenista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_almacenista)


        val botonEntradaInsumo = findViewById<Button>(R.id.btnEntradaInsumos)
        val botonBuscaInsumo = findViewById<Button>(R.id.btnBuscarInsumo)

        val botonGeneraReporteInsumo = findViewById<Button>(R.id.btnGenerarReporteInsumos)




        botonBuscaInsumo.setOnClickListener{
            val intento3 = Intent(this, listaActivity::class.java)
            startActivity(intento3)
        }



        botonGeneraReporteInsumo.setOnClickListener{
            val intento5 = Intent(this, reportesActivity::class.java)
            startActivity(intento5)
        }
        botonEntradaInsumo.setOnClickListener{
            val intent = Intent(this, Entrada::class.java)
            startActivity(intent)
        }
    }
}