package br.com.alura.api2.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.RetroFitInicializador

class PesquisaViewModel : ViewModel()  {

    private val _filmesPesquisados = MutableLiveData<List<Filme>>()
    private val _error = MutableLiveData<Boolean>()

    val filmesPesquisados: LiveData<List<Filme>>
        get() = _filmesPesquisados

    val error: LiveData<Boolean>
        get() = _error


    init {
        getFilmesPesquisados("")
    }

    private fun onError() {
        _error.value = true
    }

    private fun FilmesPopulares(filmes: List<Filme>) {
        _filmesPesquisados.value = filmes
    }

    fun getFilmesPesquisados(nome : String) {
        RetroFitInicializador.getFilmesPesquisados(
            nome,
            ::FilmesPopulares,
            ::onError
        )
    }

}