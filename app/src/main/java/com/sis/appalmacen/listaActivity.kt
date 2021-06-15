package com.sis.appalmacen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.pruebaalaapi.api.insumo
import com.example.pruebaalaapi.api.listaInsumo
import com.sis.appalmacen.Api.APIService
import com.sis.appalmacen.components.ALAdapter
import com.sis.appalmacen.components.RCAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class listaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val botonSalir = findViewById<Button>(R.id.btnSalir)
        val botonMenu = findViewById<Button>(R.id.btnMenu)
        CargaAlmacen()

        botonSalir.setOnClickListener {
            finish()
        }

        botonMenu.setOnClickListener{
            val intento1 = Intent(this, activityAlmacenista::class.java)
            startActivity(intento1)
        }

    }
    private fun CargaAlmacen(){
        CoroutineScope(Dispatchers.IO).launch {
            val service = getretrofit().create(APIService::class.java).getAlmacen("Almacen/")

            runOnUiThread{

                if(service.isSuccessful){

                    var data=service.body()!!
                    var obj=service.body()!!
                    showsuc(obj)




                    // binding.tvNombre.text=data[0].nombreinsumo.toString()
                    //binding.tvPrecio.text=data[0].cantidad.toString()



                }else{
                    showerr()

                }
            }

        }


    }
    private fun showerr() {
        Toast.makeText(this, "No jalo", Toast.LENGTH_SHORT).show()
    }

    private fun showsuc(obj: insumo) {
        var lv_datos = findViewById<ListView>(R.id.lv_almacen)

        var adapter= ALAdapter(this, obj)
        lv_datos.adapter=adapter


    }

    private fun getretrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(" http://167.172.154.123:8082")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



    }
}