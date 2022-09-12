package br.com.alura.api2.ui.data.network.response

import br.com.alura.api2.ui.data.model.Filme
import com.google.gson.annotations.SerializedName


data class FilmesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Filme>,
    @SerializedName("total_pages") val pages: Int
)