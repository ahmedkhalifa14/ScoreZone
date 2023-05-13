package com.example.scorezone.repository

import com.example.scorezone.models.Match
import com.example.scorezone.utils.Resource

interface ApiRepo {
    suspend fun getAllMatches():Resource<Match>
}