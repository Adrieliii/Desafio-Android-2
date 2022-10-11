package br.com.alura.api2.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.*
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.RetroFitInicializador
import br.com.alura.api2.ui.recyclerview.adapter.AdapterFilmes
import br.com.alura.api2.ui.viewmodel.FilmesViewModel

class FilmesFragment : Fragment() {
   /* lateinit var factory: ViewModelProvider.Factory*/

    private lateinit var filmesPopulares: RecyclerView
    private lateinit var filmesPopularesAdapter: AdapterFilmes
    private lateinit var filmesPopularesLayout: GridLayoutManager
   /* private lateinit var viewModel: FilmesViewModel*/

    private var filmesPopularesPage = 1


   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FilmesViewModel::class.java)

    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_filmes, container, false)

        filmesPopulares = view.findViewById(R.id.filmes_populares)
        filmesPopularesLayout = GridLayoutManager(context, 3)
        filmesPopulares.layoutManager = filmesPopularesLayout
        filmesPopularesAdapter =
            AdapterFilmes(mutableListOf()) { filme -> mostraDetalhesFilmes(filme) }
        filmesPopulares.adapter = filmesPopularesAdapter

        getFilmesPopulares()

            return view
    }

   /* override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.filmesPopulares.observe(viewLifecycleOwner, Observer { filmes ->
            filmesPopularesAdapter.chamaFilmes(filmes)
            anexaFilmesPopulares()
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { onError() })
    }*/

    fun getFilmesPopulares() {
        RetroFitInicializador.getFilmesPopulares(
            filmesPopularesPage,
            onSuccess = ::FilmesPopulares,
            onError = ::onError
        )
    }

    private fun FilmesPopulares(filmes: List<Filme>) {
        filmesPopularesAdapter.chamaFilmes(filmes)
        anexaFilmesPopulares()
    }

    private fun onError() {
        Toast.makeText(activity, getString(R.string.filmes_erro), Toast.LENGTH_SHORT).show()
    }

    private fun anexaFilmesPopulares() {
        filmesPopulares.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = filmesPopularesLayout.itemCount
                val visibleItemCount = filmesPopularesLayout.childCount
                val firstVisibleItem = filmesPopularesLayout.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    filmesPopulares.removeOnScrollListener(this)
                    filmesPopularesPage++
                   /* viewModel.getFilmesPopulares(filmesPopularesPage)*/
                    getFilmesPopulares()
                }
            }
        })
    }


    private fun mostraDetalhesFilmes(filme: Filme) {
        val intent = Intent(activity, DetalheFilmeActivity::class.java)
        intent.putExtra(FILME_ID, filme.id)
        intent.putExtra(FILME_BACKDROP, filme.backdropPath)
        intent.putExtra(FILME_POSTER, filme.posterPath)
        intent.putExtra(FILME_TITULO, filme.title)
        intent.putExtra(FILME_RATING, filme.rating)
        intent.putExtra(FILME_DATA, filme.releaseDate)
        intent.putExtra(FILME_OVERVIEW, filme.overview)
        startActivity(intent)
    }
}




