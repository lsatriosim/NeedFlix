package com.example.needflix

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drakor(
    val title: String,
    val releaseYear: Int,
    val jumlahEpisode: String,
    val genre: String,
    val poster: String,
    val castName: String,
    val productionStudio: String,
    val sinopsis: String,
    val posterInt : Int
) : Parcelable

