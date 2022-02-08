package com.rendonsoft.testelektra.utils.room.instance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rendonsoft.testelektra.utils.room.model.PokemonLocal
import com.rendonsoft.testelektra.utils.room.dao.PokemonDao

@Database(entities = [PokemonLocal::class], version = 2, exportSchema = false)
abstract class TestElektraDataBase : RoomDatabase() {

    abstract fun pokemon() : PokemonDao

    companion object {

        @Synchronized
        fun getRoomInstance(context: Context): TestElektraDataBase =
             Room.databaseBuilder(
                    context.applicationContext,
                    TestElektraDataBase::class.java,
                    "TestElektraDataBase"
                )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
}