package com.sis.appalmacen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sis.appalmacen.databinding.ActivityJefeComprasBinding


class loginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textemail = findViewById<EditText>(R.id.textInputCorreo)

        val loginboton = findViewById<Button>(R.id.btnLogin)


        loginboton.setOnClickListener {
            if(textemail.text.toString()=="Jefe de compras"){
                val intent = Intent(
                    this,
                    jefeComprasActivity::class.java
                )
                startActivity(intent)
            }
            if(textemail.text.toString()=="Jefe de obras"){
                val intent = Intent(this, jefeObraActivity::class.java)
                startActivity(intent)
            }
            if(textemail.text.toString()=="Almacenista"){
                val intent = Intent(this, activityAlmacenista::class.java)
                startActivity(intent)
            }
            if(textemail.text.toString()=="Residente"){
                val intent = Intent(this, reportesActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "No existe usuario", Toast.LENGTH_SHORT).show()
            }

        }
    }



}