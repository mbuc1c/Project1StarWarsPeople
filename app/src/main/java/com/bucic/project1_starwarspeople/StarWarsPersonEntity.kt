package com.bucic.project1_starwarspeople

import java.io.Serializable

data class StarWarsPersonEntity(
    val name: String,
    val height: String,
    val gender: String
) : Serializable
