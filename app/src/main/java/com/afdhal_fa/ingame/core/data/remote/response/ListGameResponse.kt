package com.afdhal_fa.ingame.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListGameResponse(

    @field:SerializedName("count")
    val count: Number,

    @field:SerializedName("results")
    val results: List<GameResponse>
)