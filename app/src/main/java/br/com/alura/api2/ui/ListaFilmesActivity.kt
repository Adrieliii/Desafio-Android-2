package br.com.alura.api2.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.api2.R
import br.com.alura.api2.ui.dao.FilmesDao
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.FilmesService
import br.com.alura.api2.ui.data.network.RetroFitInicializador
import br.com.alura.api2.ui.recyclerview.adapter.Adapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TITULO_APPBAR = "Filmes"
private val dao = FilmesDao()


class ListaFilmesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)
        title = TITULO_APPBAR

        val recyclerView = findViewById<RecyclerView>(R.id.activity_lista_filmes_recyclerview)

        getData()

        val adapter = Adapter(context = this, filmes = dao.buscaTodos())
        recyclerView.adapter = adapter
    }

     fun getData() {
       val retrofitClient = RetroFitInicializador
           .getRetroFit("https://api.themoviedb.org/3/")

         val endpoint = retrofitClient.create(FilmesService::class.java)
         val call = endpoint.getFilmesPopulares("9106a44c761c36bbb02f24c16958a56a")

         call.enqueue(object : Callback<List<Filme>> {

             override fun onFailure(call: Call<List<Filme>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
             }

             override fun onResponse(call: Call<List<Filme>>, response: Response<List<Filme>>) {
                 response.body()?.forEach{
                 }
             }
         })

    }


    //        val call  = RetroFitInicializador().filmesService()
//            .obterFilmesPopulares("9106a44c761c36bbb02f24c16958a56a")
//        call.enqueue("")
}


