package com.rendonsoft.testelektra.utils.room.dao

import androidx.room.*
import com.rendonsoft.testelektra.utils.room.model.PokemonLocal

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonLocal")
    fun getAll() : List<PokemonLocal>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addPokemons(list : List<PokemonLocal>)

    /*<><<<<<<>SXDd
    @Update(onConflict = OnConflictStrategy.ABORT)
    @Query("Update PokemonLocal SET isFavorite= :isFavorite WHERE name= :name ")
    fun updateFavorite(name : String, isFavorite: Boolean)*/
}