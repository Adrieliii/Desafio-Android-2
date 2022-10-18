package br.com.alura.api2.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.com.alura.api2.R
import br.com.alura.api2.ui.*
import br.com.alura.api2.ui.data.database.AppDatabase
import br.com.alura.api2.ui.data.model.Favoritos
import br.com.alura.api2.ui.recyclerview.adapter.AdapterFavoritos
import br.com.alura.api2.ui.viewmodel.FavoritosViewModel


class FavoritosFragment : Fragment() {
    private lateinit var favoritos: RecyclerView
    private lateinit var favoritosAdapter: AdapterFavoritos
    private lateinit var vazio: TextView
    /*private lateinit var viewModel: FavoritosViewModel*/

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java,
            "meusfilmes.db"
        ).allowMainThreadQueries().build()
    }

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoritosViewModel::class.java)
    }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favoritos, container, false)

        vazio = view.findViewById(R.id.vazio)
        favoritos = view.findViewById(R.id.favoritos)
        favoritos.layoutManager = GridLayoutManager(context, 3)
        favoritosAdapter =
            AdapterFavoritos { filme -> mostraDetalhesFilmes(filme) }
        favoritos.adapter = favoritosAdapter


        return view
    }

  /*  override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.all.observe(viewLifecycleOwner, Observer { favoritos ->
            favoritosAdapter.items(favoritos)
        })
    }*/

    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            /*viewModel.getFavoritos()*/
            getFavoritos()
        }
    }

    override fun onResume() {
        super.onResume()
       /* viewModel.getFavoritos()*/
        getFavoritos()

    }

    private fun getFavoritos() {
        val filmes = db.filmeDAO().getAll()

        favoritosAdapter.items = filmes.map { filme ->
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

        favoritosAdapter.notifyDataSetChanged()

        vazio.isVisible = favoritosAdapter.items.isEmpty()
    }

    private fun mostraDetalhesFilmes(item: Favoritos) {
        val intent = Intent(activity, DetalheFilmeActivity::class.java)
        intent.putExtra(FILME_ID, item.id)
        intent.putExtra(FILME_BACKDROP, item.backdropPath)
        intent.putExtra(FILME_POSTER, item.posterPath)
        intent.putExtra(FILME_TITULO, item.title)
        intent.putExtra(FILME_RATING, item.rating)
        intent.putExtra(FILME_DATA, item.releaseDate)
        intent.putExtra(FILME_OVERVIEW, item.overview)
        startActivity(intent)
    }


}