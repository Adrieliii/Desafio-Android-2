package br.com.alura.api2.ui.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetroFitInicializador {

    companion object {

        fun getRetroFit(path: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
//        private val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.themoviedb.org/3/")
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//
//
//    fun filmesService () : FilmesService{
//        return retrofit.create(FilmesService::class.java)
//    }
}