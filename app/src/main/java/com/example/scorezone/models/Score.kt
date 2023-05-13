package com.example.scorezone.models

data class Score(
    val duration: String,
    val fullTime: FullTime,
    val halfTime: HalfTime,
    val winner: Any
)