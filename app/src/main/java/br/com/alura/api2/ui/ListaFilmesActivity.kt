package br.com.alura.api2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.api2.R
import br.com.alura.api2.ui.fragment.FavoritosFragment
import br.com.alura.api2.ui.fragment.FilmesFragment
import br.com.alura.api2.ui.fragment.PesquisaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val FILMES_FRAGMENT = "filmes_fragment"
private const val FAVORITOS_FRAGMENT = "favoritos_fragment"
private const val PESQUISA_FRAGMENT = "pesquisa_fragment"

@Suppress("DEPRECATION")
class ListaFilmesActivity : AppCompatActivity() {

    private lateinit var botaoNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        botaoNavView = findViewById(R.id.botao_nav_view)
        botaoNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                    R.id.favoritos -> mostraFavoritosFragment()
                    R.id.filmes -> mostraFilmesFragment()
                    R.id.pesquisa -> mostraPesquisaFragment()
            }
            return@setOnNavigationItemSelectedListener true
        }

        mostraFilmesFragment()
    }

    private fun mostraFilmesFragment() {
        val transicao = supportFragmentManager.beginTransaction()

        val fragment = supportFragmentManager.findFragmentByTag(FILMES_FRAGMENT)
        val favoritosFragment = supportFragmentManager.findFragmentByTag(FAVORITOS_FRAGMENT)
        val pesquisaFragment = supportFragmentManager.findFragmentByTag(PESQUISA_FRAGMENT)

        favoritosFragment?.let { transicao.hide(it) }
        pesquisaFragment?.let { transicao.hide(it) }

        if (fragment == null) {
            transicao.add(R.id.fragment_container, FilmesFragment(), FILMES_FRAGMENT)
        } else {
            transicao.show(fragment)
        }
        transicao.commit()
    }

    private fun mostraFavoritosFragment() {
        val transicao = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(FAVORITOS_FRAGMENT)
        val filmesFragment = supportFragmentManager.findFragmentByTag(FILMES_FRAGMENT)
        val pesquisaFragment = supportFragmentManager.findFragmentByTag(PESQUISA_FRAGMENT)

        filmesFragment?.let { transicao.hide(it) }
        pesquisaFragment?.let { transicao.hide(it) }

        if (fragment == null) {
            transicao.add(R.id.fragment_container, FavoritosFragment(), FAVORITOS_FRAGMENT)
        } else {
            transicao.show(fragment)
        }
        transicao.commit()
    }

    private fun mostraPesquisaFragment() {
        val transicao = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(PESQUISA_FRAGMENT)
        val filmesFragment = supportFragmentManager.findFragmentByTag(FILMES_FRAGMENT)
        val favoritosFragment = supportFragmentManager.findFragmentByTag(FAVORITOS_FRAGMENT)

        filmesFragment?.let { transicao.hide(it) }
        favoritosFragment?.let { transicao.hide(it) }

        if (fragment == null) {
            transicao.add(R.id.fragment_container, PesquisaFragment(), PESQUISA_FRAGMENT)
        } else {
            transicao.show(fragment)
        }
        transicao.commit()
    }

    }






