package br.com.alura.api2.ui.viewmodel

import android.app.Application
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import br.com.alura.api2.ui.data.database.AppDatabase
import br.com.alura.api2.ui.data.model.Favoritos

class FavoritosViewModel(application: Application) : AndroidViewModel(application) {

    private val _all = MutableLiveData<List<Favoritos>>()
    val all: LiveData<List<Favoritos>>
        get() = _all


    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "mymovies.db"
        ).allowMainThreadQueries().build()
    }

  /*  init {
        getFavoritos()
    }*/

   /* fun getFavoritos() {
        val filmes = db.filmeDAO().getAll()

        favoritosAdapter.items =
            filmes.map { filmes ->
                Favoritos(
                    filmes.id,
                    filmes.title,
                    filmes.overview,
                    filmes.posterPath,
                    filmes.backdropPath,
                    filmes.rating,
                    filmes.releaseDate
                )
            }

        _all.value = ?
       *//* vazio.isVisible = favoritosAdapter.items.isEmpty()*//*
    }*/

}


