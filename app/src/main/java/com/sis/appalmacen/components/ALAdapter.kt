package com.sis.appalmacen.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.pruebaalaapi.api.RequicicioncompraItem
import com.example.pruebaalaapi.api.insumoItem
import com.sis.appalmacen.R
import kotlinx.android.synthetic.main.lista_almacen.view.*
import kotlinx.android.synthetic.main.lista_insumos.view.*
import kotlinx.android.synthetic.main.lista_rc.view.*

class ALAdapter(private val mcontext: Context, private val listaE:List<insumoItem>): ArrayAdapter<insumoItem>(mcontext,0,listaE) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mcontext).inflate(R.layout.lista_almacen, parent, false)
        val lista = listaE[position]
        layout.tv_nombreA.text = lista.nombreinsumo
        layout.tv_cantidadA.text = lista.cantidad.toString()
        layout.tv_Precioua.text = lista.precioUnitario.toString()
        layout.tv_partidaA.text = lista.partida
        layout.tv_importeA.text = lista.importe.toString()
        return layout

    }
}