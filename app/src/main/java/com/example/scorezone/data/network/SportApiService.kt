package com.example.scorezone.data.network

import com.example.scorezone.models.Match
import retrofit2.http.GET

interface SportApiService {
    @GET("matches")
    suspend fun getAllMatches(): Match
}