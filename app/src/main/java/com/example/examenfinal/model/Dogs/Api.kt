package com.example.examenfinal.model.Dogs

import com.example.examenfinal.model.Raza
import com.example.examenfinal.model.Raza_detail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {

    @GET
    fun getRazaApiary(
        @Url url: String?
    ): Call<ArrayList<Raza>>

    @GET("dogs_list/dog_detail/{id}")
    fun getRazaDetailApiary(
        @Path("id") id: String?
    ): Call<Raza_detail>



}