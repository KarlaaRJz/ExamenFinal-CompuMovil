package com.example.examenfinal.model

import com.google.gson.annotations.SerializedName

data class Raza(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("raza")
    var raza: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null
)
