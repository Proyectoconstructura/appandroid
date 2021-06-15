package com.sis.appalmacen.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.pruebaalaapi.api.Requicicioncompra
import com.example.pruebaalaapi.api.RequicicioncompraItem
import com.example.pruebaalaapi.api.insumoItem
import com.example.pruebaalaapi.api.listaInsumoItem
import com.sis.appalmacen.R
import kotlinx.android.synthetic.main.lista_compra.view.*
import kotlinx.android.synthetic.main.lista_rc.view.*

class EAdapter(private val mcontext: Context, private val listaE:List<RequicicioncompraItem>): ArrayAdapter<RequicicioncompraItem>(mcontext,0,listaE) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mcontext).inflate(R.layout.lista_rc,parent,false)
        val lista=listaE[position]
        layout.tv_nombrerc.text=lista.nombreinsumo
        layout.tv_cantidadrc.text=lista.cantidad.toString()
        layout.tv_umrc.text=lista.unidad
        layout.tv_fecha.text=lista.fecha
        return  layout

    }
}