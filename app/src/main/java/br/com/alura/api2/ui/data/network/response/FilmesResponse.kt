package br.com.alura.api2.ui.data.network.response

import com.squareup.moshi.Json

class FilmeResponse(
    @field:Json(name = "poster_path") val caminhoPoster: String, @field:Json(
        name = "original_title"
    ) val tituloOriginal: String
)