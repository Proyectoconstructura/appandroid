package com.sis.appalmacen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.pruebaalaapi.api.insumo
import com.sis.appalmacen.Api.APIService

import com.sis.appalmacen.components.INAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class consultainventario : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultainventario)

        CargaListaI()


    }
    private fun getretrofit():Retrofit {
        return  Retrofit.Builder()
            .baseUrl(" http://167.172.154.123:8082")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



    }
    private fun CargaListaI(){
        CoroutineScope(Dispatchers.IO).launch {
            val service = getretrofit().create(APIService::class.java).getinsumo("almacen/")

            runOnUiThread{

                if(service.isSuccessful){
                    var data=service.body()!!
                    showsuc(data)

                }else{
                    showerr()

                }
            }

        }


    }

    private fun showerr() {
        Toast.makeText(this, "Error al cargar la API", Toast.LENGTH_SHORT).show()
    }

    private fun showsuc(data: insumo) {
        var lista=findViewById<ListView>(R.id.lv_invdentario)
        var adapter= INAdapter(this,data)
        lista.adapter=adapter
        Toast.makeText(this, "asdasd", Toast.LENGTH_LONG).show()



    }
}