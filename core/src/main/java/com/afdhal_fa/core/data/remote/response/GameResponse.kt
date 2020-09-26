package com.afdhal_fa.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description_raw")
    val description: String?,

    @field:SerializedName("released")
    val released: String?,

    @field:SerializedName("rating")
    val rating: String,

    @field:SerializedName("background_image")
    val imageUrl: String
)

