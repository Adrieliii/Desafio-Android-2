package br.com.alura.api2.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.com.alura.api2.R
import br.com.alura.api2.ui.*
import br.com.alura.api2.ui.data.database.AppDatabase
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.RetroFitInicializador
import br.com.alura.api2.ui.recyclerview.adapter.AdapterFilmes
import br.com.alura.api2.ui.recyclerview.adapter.AdapterPesquisa
import br.com.alura.api2.ui.viewmodel.FilmesViewModel
import br.com.alura.api2.ui.viewmodel.PesquisaViewModel
import java.util.*

class PesquisaFragment : Fragment() {

    private lateinit var filmesPesquisados : RecyclerView
    private lateinit var filmesAdapter: AdapterPesquisa
    private lateinit var filmesLayout: GridLayoutManager
    private  lateinit var filmess: MutableList<Filme>
    private lateinit var viewModel: PesquisaViewModel

    private var filmesPage = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PesquisaViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pesquisa, container, false)

        filmesPesquisados = view.findViewById(R.id.filmes_pesquisados)
        filmesLayout = GridLayoutManager(context, 3)
        filmesPesquisados.layoutManager = filmesLayout
        filmess = arrayListOf<Filme>()
        filmesAdapter =
            AdapterPesquisa{ filme -> mostraDetalhesFilmes(filme) }
        filmesPesquisados.adapter = filmesAdapter

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val search = view.findViewById<SearchView>(R.id.searchView)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                getFilmesPesquisados(query?:"")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

 /*   override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.filmesPesquisados.observe(viewLifecycleOwner, Observer { filmes ->
            filmesAdapter.chamaFilmes(filmes)
            anexaFilmesPopulares()
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { onError() })
    }*/

    fun getFilmesPesquisados(nome : String) {
        RetroFitInicializador.getFilmesPesquisados(
            nome,
            onSuccess = ::FilmesPopulares,
            onError = ::onError
        )
    }

    private fun FilmesPopulares(filmes: List<Filme>) {
        filmesAdapter.chamaFilmes(filmes)
        anexaFilmesPopulares()
    }

    private fun onError() {
        Toast.makeText(activity, getString(R.string.filmes_erro), Toast.LENGTH_SHORT).show()
    }

    private fun anexaFilmesPopulares() {
        filmesPesquisados.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = filmesLayout.itemCount
                val visibleItemCount = filmesLayout.childCount
                val firstVisibleItem = filmesLayout.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    filmesPesquisados.removeOnScrollListener(this)
                    filmesPage++
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









