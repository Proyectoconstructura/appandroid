package com.sis.appalmacen.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.example.pruebaalaapi.api.listaInsumoItem
import com.sis.appalmacen.R
import kotlinx.android.synthetic.main.lista_compra.view.*
import kotlinx.android.synthetic.main.lista_insumos.view.*


class RCAdapter(private val mcontext: Context, private val listaRI:List<listaInsumoItem>):ArrayAdapter<listaInsumoItem>(mcontext,0,listaRI) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mcontext).inflate(R.layout.lista_compra,parent,false)
        val lista=listaRI[position]
        layout.tv_nombrec.text=lista.nombreinsumo
        layout.tv_cantidadc.text= lista.cantidad.toString()
        layout.tv_partidac.text=lista.partida


        return layout
    }
}