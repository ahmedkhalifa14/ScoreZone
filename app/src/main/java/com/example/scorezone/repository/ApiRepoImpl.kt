package com.example.scorezone.repository

import com.example.scorezone.data.network.SportApiService
import com.example.scorezone.models.Match
import com.example.scorezone.utils.Resource
import com.example.scorezone.utils.Utils.tryCatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ApiRepoImpl @Inject constructor(
    private val apiService: SportApiService
) : ApiRepo {
    override suspend fun getAllMatches(): Resource<Match> =
        withContext(Dispatchers.IO) {
            tryCatch {
                val result = apiService.getAllMatches()
                Resource.Success(result)
            }
        }
}