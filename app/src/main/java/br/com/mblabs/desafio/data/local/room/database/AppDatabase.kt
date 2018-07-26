package br.com.mblabs.desafio.data.local.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.mblabs.desafio.data.local.room.dao.ItensDao

@Database(
        entities = [ItensDao::class],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ItensDao(): ItensDao
}