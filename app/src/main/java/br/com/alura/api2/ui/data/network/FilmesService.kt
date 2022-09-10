package br.com.alura.api2.ui.data.network

import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.response.FilmesResult
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Query

interface FilmesService {

    @GET("movie/popular")
    fun getFilmesPopulares(@Query("api_key") chaveApi: String?) : Call<List<FilmesResult>>

}