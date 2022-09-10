package br.com.alura.api2.ui.data.network.response

import com.squareup.moshi.Json

class FilmesResult(@field:Json(name = "results") val resultadoFilmes: List<FilmeResponse>)