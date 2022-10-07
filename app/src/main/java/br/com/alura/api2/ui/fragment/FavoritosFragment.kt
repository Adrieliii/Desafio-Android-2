package br.com.alura.api2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.com.alura.api2.R
import br.com.alura.api2.ui.data.database.AppDatabase
import br.com.alura.api2.ui.data.model.Favoritos
import br.com.alura.api2.ui.recyclerview.adapter.AdapterFavoritos


class FavoritosFragment : Fragment() {
    private lateinit var favoritos: RecyclerView
    private lateinit var favoritosAdapter: AdapterFavoritos

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java,
            "meusfilmes.db"
        ).allowMainThreadQueries().build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favoritos, container, false)

        favoritos = view.findViewById(R.id.favoritos)
        favoritos.layoutManager = GridLayoutManager(context, 3)
        favoritosAdapter = AdapterFavoritos(listOf())
        favoritos.adapter = favoritosAdapter

        getFavoritos()
        return view
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            getFavoritos()
        }
    }

    override fun onResume() {
        super.onResume()
        getFavoritos()
    }

    private fun getFavoritos() {
        val filmes = db.filmeDAO().getAll()
        val favoritos = mutableListOf<Favoritos>()

        favoritos.addAll(
            filmes.map { filme ->
                Favoritos(
                    filme.id,
                    filme.title,
                    filme.overview,
                    filme.posterPath,
                    filme.backdropPath,
                    filme.rating,
                    filme.releaseDate
                )
            }
        )

        favoritosAdapter.atualizaItems(favoritos)
    }


}