package com.example.lecture4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface CountryService {

    @GET("all")
    fun getCountries(): Call<List<Country>>
}

interface NameService {

    @GET("name/{country}")
    fun getNames(@Path("country") name: String ) : Call<List<Name>>

}