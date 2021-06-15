package com.sis.appalmacen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class passwordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        val contraseñaboton = findViewById<Button>(R.id.btnContra)


        contraseñaboton.setOnClickListener{
            val intento1 = Intent(this, activityAlmacenista::class.java)
            startActivity(intento1)
        }


    }
}