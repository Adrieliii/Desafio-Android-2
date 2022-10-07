package br.com.alura.api2.ui.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.alura.api2.ui.data.database.dao.FilmeDAO
import br.com.alura.api2.ui.data.model.FilmeEntidade

@Database(entities = [FilmeEntidade::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmeDAO(): FilmeDAO
}