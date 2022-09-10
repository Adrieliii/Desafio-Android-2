package br.com.alura.api2.ui.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetroFitInicializador {

    companion object {

        fun getRetroFit(path: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }

}