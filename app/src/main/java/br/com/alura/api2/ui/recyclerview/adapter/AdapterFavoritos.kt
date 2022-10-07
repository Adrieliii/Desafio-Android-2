package br.com.alura.api2.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.data.model.Favoritos
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class AdapterFavoritos(
    private var items: List<Favoritos>
) : RecyclerView.Adapter<AdapterFavoritos.FavoritosHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_favoritos, parent, false)
        return FavoritosHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FavoritosHolder, position: Int) {
        holder.bind(items[position])
    }

    fun atualizaItems(items: List<Favoritos>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class FavoritosHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.item_favoritos_poster)

        fun bind(item: Favoritos) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${item.posterPath}")
                .transform(CenterCrop())
                .into(poster)
        }
    }
}

