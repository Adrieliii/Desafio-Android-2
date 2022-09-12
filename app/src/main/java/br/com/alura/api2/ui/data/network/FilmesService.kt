package br.com.alura.api2.ui.data.network

import br.com.alura.api2.ui.data.network.response.FilmesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmesService {

    @GET("movie/popular")
    fun getFilmesPopulares(
        @Query("api_key") apiKey: String = "9106a44c761c36bbb02f24c16958a56a",
        @Query("page") page: Int
    ): Call<FilmesResponse>

}