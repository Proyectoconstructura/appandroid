package com.sis.appalmacen

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.size
import com.example.pruebaalaapi.api.listaInsumo
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.sis.appalmacen.Api.APIService
import com.sis.appalmacen.components.RCAdapter
import kotlinx.android.synthetic.main.lista_compra.*
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

class RequisicionCompra : AppCompatActivity() {

    //private lateinit var binding: ActivityRequisicionCompraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requisicion_compra)

        var btnguarda=findViewById<Button>(R.id.btnGuarda)
        CargaCompra()


        btnguarda.setOnClickListener{
            registracompra()
            //Toast.makeText(this, "Se a guardado la requisicion", Toast.LENGTH_SHORT).show()
        }




    }

    private fun registracompra() {
        var lv_datos = findViewById<ListView>(R.id.lv_datosc)
        var datos=lv_datos.adapter.count


        for (num in 0..datos-1){
            if (lv_datos[num].cb_ok.isChecked){

                postlist(lv_datos[num].tv_nombrec.text.toString() ,lv_datos[num].tv_cantidadc.text.toString())

            }
        }

    }

    private fun CargaCompra(){
        CoroutineScope(Dispatchers.IO).launch {
            val service = getretrofit().create(APIService::class.java).getLista("Listainsumo/")

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

    private fun showsuc(obj: listaInsumo) {
        var lv_datos = findViewById<ListView>(R.id.lv_datosc)

        Toast.makeText(this, "asdasd", Toast.LENGTH_LONG).show()


        var adapter= RCAdapter(this, obj)
        lv_datos.adapter=adapter


    }

    private fun getretrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(" http://167.172.154.123:8082")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



    }
    fun postlist(  nombre:String , cantidad:String) {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl( "http://167.172.154.123:8082")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()

        jsonObject.put("nombreinsumo", nombre)
        jsonObject.put("fecha", null)
        jsonObject.put("cantidad", cantidad.toInt())
        jsonObject.put("comprador", null)
        jsonObject.put("numero", null)
        jsonObject.put("solicitante", null)
        jsonObject.put("revisor", null)
        jsonObject.put("autorizante", null)
        jsonObject.put("observaciones", null)
        jsonObject.put("centro_costo", null)
        jsonObject.put("prioridad", null)
        jsonObject.put("unidad", null)

/*
        nombrealmcen: ,
        nombreinsumo: ,
        nomproveedor:,
        cantidad: 20,
        fecha_entrada:,
        importe: ,
        precio_unitario
 */
        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.setCompra(requestBody)

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