package com.example.lecture4

import retrofit2.Call
import retrofit2.http.GET

interface CountryService {

    @GET("all")
    fun getCountries(): Call<List<Country>>
}