package br.com.alura.api2.ui.recyclerview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.data.model.Filme
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

  class AdapterPesquisa
      (private val onFilmeClick: (filme: Filme) -> Unit
    ) : RecyclerView.Adapter<AdapterPesquisa.FilmeViewHolder>() {

       var filmes: MutableList<Filme> = arrayListOf()


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_filme, parent, false)
            return FilmeViewHolder(view)
        }

        override fun getItemCount(): Int = filmes.size

        override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
            holder.bind(filmes[position])
        }

        @SuppressLint("NotifyDataSetChanged")
        fun chamaFilmes(filmes: List<Filme>) {
            this.filmes.clear()
            this.filmes.addAll(filmes)
            notifyDataSetChanged()
        }

        inner class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val poster: ImageView = itemView.findViewById(R.id.item_filme_poster)

            fun bind(filme: Filme) {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w342${filme.posterPath}")
                    .transform(CenterCrop())
                    .into(poster)

                itemView.setOnClickListener { onFilmeClick.invoke(filme) }
            }
        }

  }

