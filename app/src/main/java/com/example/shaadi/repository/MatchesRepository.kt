package com.example.shaadi.repository

import com.example.shaadi.models.Matches
import com.example.shaadi.services.retrofit.RetrofitBuilder
import com.example.shaadi.services.room.MatchesDao

class MatchesRepository(private val dao : MatchesDao) {

    suspend fun getMatches(isConnected: Boolean) : Matches? {
         if (isConnected) {
            val localResponse = dao.getMatches()
             return if(localResponse == null) {
                 val remoteResponse = RetrofitBuilder.api.getMatches()
                 if(remoteResponse.isSuccessful) {
                     remoteResponse.body()?.let { dao.insertMatches(it) }
                     remoteResponse.body()
                 } else {
                     null
                 }
             } else {
                 localResponse
             }
        }
        else {
             val localResponse = dao.getMatches()
             return if (localResponse == null)
                 null
             else
                 localResponse
        }
    }

    suspend fun updateMatches(matches: Matches) {
        dao.delete()
        dao.insertMatches(matches)
    }

}