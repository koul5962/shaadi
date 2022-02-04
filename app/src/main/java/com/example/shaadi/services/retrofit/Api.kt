package com.example.shaadi.services.retrofit

import com.example.shaadi.models.Matches
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/api/?results=10")
    suspend fun getMatches() : Response<Matches>
}