package com.example.examenfinal.model

import com.google.gson.annotations.SerializedName

class Raza_detail(
    @SerializedName("raza")
    var raza: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("long_desc")
    var longDesc: String? = null
)