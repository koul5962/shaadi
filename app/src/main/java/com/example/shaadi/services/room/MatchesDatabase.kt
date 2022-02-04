package com.example.shaadi.services.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shaadi.models.Matches

@Database(entities = [Matches::class], version = 1)
@TypeConverters(Converters::class)
abstract class MatchesDatabase : RoomDatabase() {

    abstract fun matchesDao() : MatchesDao

    companion object {
        @Volatile
        private var INSTANCE : MatchesDatabase? = null
        fun getInstance(context: Context) : MatchesDatabase {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    MatchesDatabase::class.java,
                    "matchesDB")
                    .build()
            }
            return INSTANCE!!
        }
    }
}