package br.com.alura.api2.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.RetroFitInicializador

class FilmesViewModel {
    private val _filmesPopulares = MutableLiveData<List<Filme>>()
    private val _error = MutableLiveData<Boolean>()

    val error: LiveData<Boolean>
        get() = _error


    init {
        getFilmesPopulares()
    }

    val filmesPopulares: LiveData<List<Filme>>
        get() = _filmesPopulares

    private fun onError() {
        _error.value = true
    }

    private fun FilmesPopulares(filmes: List<Filme>) {
        _filmesPopulares.value = filmes
    }

    fun getFilmesPopulares(page: Int = 1) {
        RetroFitInicializador.getFilmesPopulares(
            page,
            ::FilmesPopulares,
            ::onError
        )
    }
}