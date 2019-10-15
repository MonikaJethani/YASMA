package com.monika.jethani.yasma.albums.models

import java.io.Serializable

data class Album(
    val userId: Int,
    val id: Int,
    val title: String
) : Serializable