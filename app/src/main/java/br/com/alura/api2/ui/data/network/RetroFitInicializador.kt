package br.com.alura.api2.ui.data.network


import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.response.FilmesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetroFitInicializador {

    private val api: FilmesService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(FilmesService::class.java)
    }

    fun getFilmesPopulares(
        page: Int = 1, onSuccess: (movies: List<Filme>) -> Unit,
        onError: () -> Unit
    ) {
        api.getFilmesPopulares(page = page)
            .enqueue(object : Callback<FilmesResponse> {
                override fun onResponse(
                    call: Call<FilmesResponse>,
                    response: Response<FilmesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<FilmesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })

    }

    fun getFilmesPesquisados(
        nome: String, onSuccess: (movies: List<Filme>) -> Unit,
        onError: () -> Unit
    ) {
        api.getFilmesPesquisados(nome = nome)
            .enqueue(object : Callback<FilmesResponse> {
                override fun onResponse(
                    call: Call<FilmesResponse>,
                    response: Response<FilmesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<FilmesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })

    }
}