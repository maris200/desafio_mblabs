package br.com.mblabs.desafio.data.local.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity

data class ListItensEntity(
        @PrimaryKey(autoGenerate = true)
        val id_room: Long = 0,
        val oldPrice: Double? = null,
        val name: String? = null,
        val description: String? = null,
        val currentPrice: Double? = null,
        val id: Int? = null,
        val url: String? = null
)
