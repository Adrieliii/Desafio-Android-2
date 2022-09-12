package br.com.alura.api2.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import br.com.alura.api2.R
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.RetroFitInicializador


private const val TITULO_APPBAR = "Filmes"


class ListaFilmesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        RetroFitInicializador.getFilmesPopulares(
            onSuccess = ::FilmesPopulares,
            onError = ::onError
        )
    }

    private fun FilmesPopulares(filmes: List<Filme>) {
        Log.d("ListaFilmesActivity", "Filmes: $filmes")
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.filmes_erro), Toast.LENGTH_SHORT).show()
    }


}


