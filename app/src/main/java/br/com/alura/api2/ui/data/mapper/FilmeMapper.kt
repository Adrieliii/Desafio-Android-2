package br.com.alura.api2.ui.data.mapper

import br.com.alura.api2.ui.data.model.Filme
import br.com.alura.api2.ui.data.network.response.FilmeResponse
import java.util.ArrayList

object FilmeMapper {
    fun deResponseParaDominio(listaFilmeResponse: List<FilmeResponse?>?): List<Filme> {
        val listaFilmes: MutableList<Filme> = ArrayList()
        for (filmeResponse in listaFilmeResponse!!) {
            val filme = filmeResponse?.let { Filme(it.tituloOriginal,filmeResponse.caminhoPoster) }
            if (filme != null) {
                listaFilmes.add(filme)
            }
        }
        return listaFilmes
    }
}