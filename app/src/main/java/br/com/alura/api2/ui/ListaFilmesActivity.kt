package br.com.alura.api2.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Transformations.map
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.dao.FilmesDao
import br.com.alura.api2.ui.data.mapper.FilmeMapper
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.FilmesService
import br.com.alura.api2.ui.data.network.RetroFitInicializador
import br.com.alura.api2.ui.data.network.response.FilmeResponse
import br.com.alura.api2.ui.data.network.response.FilmesResult
import br.com.alura.api2.ui.recyclerview.adapter.Adapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



private const val TITULO_APPBAR = "Filmes"
private val dao = FilmesDao()


class ListaFilmesActivity : AppCompatActivity() {
    private var filmesAdapter: Adapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)
        title = TITULO_APPBAR

        getData()

    }

     fun getData() {
       val retrofitClient = RetroFitInicializador
           .getRetroFit("https://api.themoviedb.org/3/")

         val endpoint = retrofitClient.create(FilmesService::class.java)
         val call = endpoint.getFilmesPopulares("9106a44c761c36bbb02f24c16958a56a")

         call.enqueue(object : Callback<List<FilmesResult>> {

             override fun onFailure(call: Call<List<FilmesResult>>, t: Throwable) {
                mostraErro()
             }

             override fun onResponse(call: Call<List<FilmesResult>>, response: Response<List<FilmesResult>>) {
                if (response.isSuccessful()){
                    /*val listaFilmes=
                    filmesAdapter?.setFilmes(FilmeMapper.deResponseParaDominio(response.body().))*/
                    configuraAdapter()
                } else{
                    mostraErro()
                }
             }
         })

    }

    private fun configuraAdapter() {
        val recyclerView = findViewById<RecyclerView>(R.id.activity_lista_filmes_recyclerview)
        filmesAdapter = Adapter(filmes = dao.buscaTodos())
        recyclerView.adapter = filmesAdapter
    }

    private fun mostraErro(){
        Toast.makeText(this, "Erro ao obter a lista de filmes", Toast.LENGTH_SHORT)
    }



}


