package br.com.mblabs.desafio.data.local.room.dao


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.mblabs.desafio.data.local.room.entity.ListItensEntity
import br.com.mblabs.desafio.data.remote.model.entity.itemList

@Dao
interface ItensDao {

    @Query("SELECT * FROM ListItensEntity")
    fun all(): List<ListItensEntity>

    @Insert
    fun add(vararg listItensEntityRoom: List<itemList?>?)

}