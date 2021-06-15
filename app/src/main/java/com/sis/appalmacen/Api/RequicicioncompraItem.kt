package com.example.pruebaalaapi.api

data class RequicicioncompraItem(
    val autorizante: String,
    val cantidad: Int,
    val centro_costo: String,
    val comprador: String,
    val fecha: String,
    val nombreinsumo: String,
    val numero: Int,
    val observaciones: String,
    val prioridad: String,
    val revisor: String,
    val solicitante: String,
    val unidad: String
)