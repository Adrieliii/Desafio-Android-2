package br.com.alura.api2.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.RetroFitInicializador
import br.com.alura.api2.ui.recyclerview.adapter.AdapterFilmes


class ListaFilmesActivity : AppCompatActivity() {
    private lateinit var filmesPopulares: RecyclerView
    private lateinit var filmesPopularesAdapter: AdapterFilmes
    private lateinit var filmesPopularesLayout: GridLayoutManager

    private var filmesPopularesPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        filmesPopulares = findViewById(R.id.filmes_populares)
        filmesPopularesLayout = GridLayoutManager(
            this,
           2
        )
        filmesPopulares.layoutManager = filmesPopularesLayout
        filmesPopularesAdapter = AdapterFilmes(mutableListOf()) { filme -> mostraDetalhesFilmes(filme)}
        filmesPopulares.adapter = filmesPopularesAdapter

        getFilmesPopulares()
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
                    getFilmesPopulares()
                }
            }
        })
    }

    private fun getFilmesPopulares() {
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
        Toast.makeText(this, getString(R.string.filmes_erro), Toast.LENGTH_SHORT).show()
    }

    private fun mostraDetalhesFilmes(filme: Filme) {
        val intent = Intent(this, DetalheFilmeActivity::class.java)
        intent.putExtra(FILME_BACKDROP, filme.backdropPath)
        intent.putExtra(FILME_POSTER, filme.posterPath)
        intent.putExtra(FILME_TITULO, filme.title)
        intent.putExtra(FILME_RATING, filme.rating)
        intent.putExtra(FILME_DATA, filme.releaseDate)
        intent.putExtra(FILME_OVERVIEW, filme.overview)
        startActivity(intent)
    }


}


