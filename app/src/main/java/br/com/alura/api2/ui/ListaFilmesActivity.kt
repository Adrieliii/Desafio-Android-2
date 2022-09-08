package br.com.alura.api2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.dao.FilmesDao
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.recyclerview.adapter.Adapter

private const val TITULO_APPBAR = "Filmes"
private val dao = FilmesDao()


class ListaFilmesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)
        title = TITULO_APPBAR

        val recyclerView = findViewById<RecyclerView>(R.id.activity_lista_filmes_recyclerview)
        val adapter = Adapter(context = this, filmes = dao.buscaTodos())
        recyclerView.adapter = adapter
    }
}