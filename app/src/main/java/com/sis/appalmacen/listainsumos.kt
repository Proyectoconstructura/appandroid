package com.sis.appalmacen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.GsonBuilder

import com.google.gson.JsonParser
import com.sis.appalmacen.Api.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.http.Url


class listainsumos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listainsumos)
        var btnguarda=findViewById<Button>(R.id.btnguarda)
        var tv_nombre=findViewById<EditText>(R.id.et_nombre)
        var tv_clave=findViewById<EditText>(R.id.et_clave)
        var tv_partida=findViewById<EditText>(R.id.et_partida)
        var tv_cantidad=findViewById<EditText>(R.id.et_cantidad)
        var tv_unidad=findViewById<EditText>(R.id.et_unidad)
        var tv_proyecto=findViewById<EditText>(R.id.et_proyecto)


        btnguarda.setOnClickListener{
            postli(tv_proyecto.text.toString(), tv_nombre.text.toString(),tv_clave.text.toString(),tv_partida.text.toString(),tv_cantidad.text.toString(),tv_unidad.text.toString())
            Toast.makeText(this, "Se a guardado la reqisicion", Toast.LENGTH_SHORT).show()


        }

    }
    fun postli(proyecto: String, clave: String, nombre: String, partida: String, cantidad: String, unidad:String) {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl( "http://167.172.154.123:8082")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("proyecto", proyecto)
        jsonObject.put("clave", clave)
        jsonObject.put("nombreinsumo", nombre)
        jsonObject.put("partida", partida)
        jsonObject.put("cantidad", cantidad.toInt())
        jsonObject.put("unidadmedida", unidad)





       /*
        proyecto: "asuyi6",
        clave: "asuyi6",
        nombreinsumo: "Tubo PVC",
        partida: "Plomeria",
        cantidad: 10,
        unidadmedida: "MT"
*/
        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.setListaInsumo(requestBody)

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
}