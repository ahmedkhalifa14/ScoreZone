package com.example.scorezone.models

data class Match(
    val filters: Filters,
    val matches: List<Matche>,
    val resultSet: ResultSet
)