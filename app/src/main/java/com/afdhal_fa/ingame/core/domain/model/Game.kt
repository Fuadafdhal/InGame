package com.afdhal_fa.ingame.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    val gameId: String,
    val name: String,
    val description: String?,
    val released: String?,
    val rating: String,
    val imageUrl: String,
    val isFavorite: Boolean
) : Parcelable