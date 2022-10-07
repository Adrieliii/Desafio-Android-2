package br.com.alura.api2.ui.data.model

data class Favoritos (
    val id: Long,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val rating: Float,
    val releaseDate: String
        )
