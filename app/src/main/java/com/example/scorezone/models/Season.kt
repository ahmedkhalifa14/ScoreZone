package com.example.scorezone.models

data class Season(
    val currentMatchday: Int,
    val endDate: String,
    val id: Int,
    val startDate: String,
    val winner: Any
)