package com.example.pruebaalaapi.api

data class entradaItem(
    val cantidad: Int,
    val fecha_entrada: String,
    val importe: Int,
    val nombrealmcen: String,
    val nombreinsumo: String,
    val nomproveedor: String,
    val precio_unitario: Int
)