package com.example.pruebaalaapi.api

data class insumoItem(
    val cantidad: Int,
    val importe: Int,
    val nombreinsumo: String,
    val partida: String,
    val precioUnitario: Int
)