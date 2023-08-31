package com.bucic.project1_starwarspeople

data class StarWarsPeopleResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<StarWarsPersonEntity>
)