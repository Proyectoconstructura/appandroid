package com.sis.appalmacen

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.pruebaalaapi.api.Requicicioncompra
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.sis.appalmacen.Api.APIService
import com.sis.appalmacen.components.EAdapter
import kotlinx.android.synthetic.main.lista_compra.view.*
import kotlinx.android.synthetic.main.lista_rc.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Entrada : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrada)


        var btnguarda=findViewById<Button>(R.id.btnGuarda)
        CargaEntrada()
        btnguarda.setOnClickListener{


        postentrada()
            Toast.makeText(this, "Guardado exitoso", Toast.LENGTH_SHORT).show()

        }


    }
    fun postlist(  nombre:String ) {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl( "http://167.172.154.123:8082")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()

        jsonObject.put("nombrealmcen", null)
        jsonObject.put("nombreinsumo", nombre)
        jsonObject.put("nomproveedor", null)
        jsonObject.put("cantidad", null)
        jsonObject.put(" fecha_entrada", null)
        jsonObject.put("importe", null)
        jsonObject.put("precio_unitario", null)



        /*
nombreinsumo: ,
fecha:
cantidad:,
comprador:,
numero:,
solicitante:,
revisor: ,
autorizante:,
observaciones:,
centro_costo: ,
prioridad: ,
unidad: "
 */
        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.setEntrada(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body().toString()

                            // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )

                    Log.d("Pretty Printed JSON :", prettyJson)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }


    private fun postentrada() {
        var lv_datos=findViewById<ListView>(R.id.lv_Rinsumo)
        var datos=lv_datos.adapter.count
        for (num in 0..datos-1){
            if (lv_datos[num].cb_entrada.isChecked){

                postlist(lv_datos[num].tv_nombrerc.text.toString() )

            }
        }
    }

    private fun CargaEntrada() {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getretrofit().create(APIService::class.java).getRc("RequisicionCompra/")

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

    private fun showsuc(obj: Requicicioncompra) {
        var lv_datos = findViewById<ListView>(R.id.lv_Rinsumo)


        var adapter= EAdapter(this, obj)
        lv_datos.adapter=adapter

    }

    private fun getretrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(" http://167.172.154.123:8082")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}
