package com.rendonsoft.testelektra.utils.room.model

import android.view.View
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PokemonLocal (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    var isFavorite : Boolean = false,
    var hasError : Boolean = false
){
    val isFavoriteView : Int
        get() { return if (isFavorite) View.VISIBLE else View.GONE}

    val hasErrorView: Int
        get() { return if(hasError) View.VISIBLE else View.GONE}
}