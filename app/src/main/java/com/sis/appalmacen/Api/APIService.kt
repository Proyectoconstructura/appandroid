package com.sis.appalmacen.Api


import com.example.pruebaalaapi.api.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET
    suspend fun getinsumo(@Url url:String): Response<insumo>

    @GET
    suspend fun getLista(@Url url:String): Response<listaInsumo>

    @GET
    suspend fun getRc(@Url url:String): Response<Requicicioncompra>
    @GET
    suspend fun getAlmacen(@Url url:String): Response<insumo>


    @POST("Listainsumo")
    suspend fun setListaInsumo(@Body RequestBody:RequestBody): Response<ResponseBody>

    @POST("Entrada")
    suspend fun setEntrada(@Body RequestBody:RequestBody): Response<ResponseBody>

    @POST("RequisicionCompra")
    suspend fun setCompra(@Body RequestBody:RequestBody): Response<ResponseBody>
}