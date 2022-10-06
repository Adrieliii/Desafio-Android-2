package br.com.alura.api2.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.RetroFitInicializador
import br.com.alura.api2.ui.fragment.FilmesFragment
import br.com.alura.api2.ui.recyclerview.adapter.AdapterFilmes

private const val FILMES_FRAGMENT = "filmes_fragment"

class ListaFilmesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        mostraFilmesFragment()
    }

    private fun mostraFilmesFragment() {
        val transicao = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(FILMES_FRAGMENT)
        if (fragment == null) {
            transicao.add(R.id.fragment_container, FilmesFragment(), FILMES_FRAGMENT)
        } else {
            transicao.show(fragment)
        }
        transicao.commit()
    }


}


