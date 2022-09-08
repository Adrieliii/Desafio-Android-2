package br.com.alura.api2.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.data.model.Filme

class Adapter(
    private val context : Context,
    private var filmes : List<Filme>
) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun vincula(filme: Filme) {
            val nome = itemView.findViewById<TextView>(R.id.item_filme_titulo)
            nome.text = filme.nome
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_filme, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.vincula(filme)
    }

    override fun getItemCount(): Int {
        return if (filmes != null && filmes!!.size > 0) filmes!!.size else 0
    }


}

