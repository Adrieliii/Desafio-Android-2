package br.com.alura.api2.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.RetroFitInicializador
import br.com.alura.api2.ui.recyclerview.adapter.AdapterFilmes


class ListaFilmesActivity : AppCompatActivity() {
    private lateinit var filmesPopulares: RecyclerView
    private lateinit var filmesPopularesAdapter: AdapterFilmes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        filmesPopulares = findViewById(R.id.filmes_populares)
        filmesPopulares.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        filmesPopularesAdapter = AdapterFilmes(listOf())
        filmesPopulares.adapter = filmesPopularesAdapter

        RetroFitInicializador.getFilmesPopulares(
            onSuccess = ::FilmesPopulares,
            onError = ::onError
        )
    }

    private fun FilmesPopulares(filmes: List<Filme>) {
        filmesPopularesAdapter.atualizaFilmes(filmes)
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.filmes_erro), Toast.LENGTH_SHORT).show()
    }


}


