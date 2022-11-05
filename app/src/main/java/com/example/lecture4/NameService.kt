package com.example.lecture4

import retrofit2.Call
import retrofit2.http.GET

interface NameService {

    @GET("name/{country}")
    fun getNames(): Call<List<Name>>

}