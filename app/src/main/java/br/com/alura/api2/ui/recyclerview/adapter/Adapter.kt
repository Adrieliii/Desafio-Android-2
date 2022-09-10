package br.com.alura.api2.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.data.model.Filme
import com.squareup.picasso.Picasso
import java.util.ArrayList

class Adapter(
    private var filmes: List<Filme>
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    fun Adapter(){
        filmes = ArrayList()
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val imagePosterFilme = itemView.findViewById(R.id.item_filme_imagem)
        fun vincula(filme: Filme) {
            val nome = itemView.findViewById<TextView>(R.id.item_filme_titulo)
            nome.text = filme.titulo
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w342/" + filme.caminhoPoster)
                .into(imagePosterFilme)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_filme, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.vincula(filme)
    }

    override fun getItemCount(): Int {
        return if (filmes != null && filmes!!.size > 0) filmes!!.size else 0
    }

    fun setFilmes(filmes: List<Filme>) {
        this.filmes = filmes
        notifyDataSetChanged()
    }


}

