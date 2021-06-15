package com.sis.appalmacen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sis.appalmacen.R.layout.activity_jefe_compras

class jefeComprasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_jefe_compras)

        val botonProcesaRequInsumo = findViewById<Button>(R.id.btnCrearRequisicionCompras)
        botonProcesaRequInsumo.setOnClickListener{
            var intent= Intent(this, RequisicionCompra::class.java)
            startActivity(intent)

        }




    }
}