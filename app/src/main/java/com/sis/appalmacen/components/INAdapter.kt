package com.sis.appalmacen.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.pruebaalaapi.api.insumoItem
import com.sis.appalmacen.R


import kotlinx.android.synthetic.main.lista_insumos.view.*

class INAdapter (private val mcontext: Context, private val listaI:List<insumoItem>): ArrayAdapter<insumoItem>(mcontext,0,listaI){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mcontext).inflate(R.layout.lista_insumos,parent,false)
        val lista=listaI[position]


        layout.tv_nInsumo.text=lista.nombreinsumo
        layout.tv_cantidad.text= lista.cantidad.toString()
        layout.tv_importe.text=lista.importe.toString()
        layout.tv_partida.text=lista.partida
        layout.tv_precioU.text=lista.precioUnitario.toString()
        return layout
    }

}