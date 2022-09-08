package br.com.alura.api2.ui.dao

import br.com.alura.api2.ui.data.model.Filme

class FilmesDao {


    fun buscaTodos() : List<Filme> {
        return filmes.toList()
    }

    companion object {
        private val filmes = mutableListOf<Filme>()
    }
}