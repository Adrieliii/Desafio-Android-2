package br.com.alura.api2.ui.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.alura.api2.ui.data.model.FilmeEntidade


@Dao
    interface FilmeDAO {
        @Query("SELECT * FROM tbl_filmes")
        fun getAll(): List<FilmeEntidade>
        @Insert
        fun insert(filme: FilmeEntidade)
        @Query("SELECT * FROM tbl_filmes WHERE id = :id LIMIT 1")
        fun findById(id: Long): FilmeEntidade?
        @Query("DELETE FROM tbl_filmes WHERE id = :id")
        fun delete(id: Long)
    }
