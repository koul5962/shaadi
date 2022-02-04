package com.example.shaadi.services.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shaadi.models.Matches
import retrofit2.Response

@Dao
interface MatchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatches(matches : Matches)

    @Query("SELECT * FROM matches WHERE id = (SELECT MAX(id) FROM matches)")
    suspend fun getMatches() : Matches

    @Query("DELETE FROM matches")
    suspend fun delete()
}