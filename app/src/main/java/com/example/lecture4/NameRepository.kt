package com.example.lecture4

import retrofit2.Call

class NameRepository constructor(
    private val nameService: NameService
) {
    fun getNames(countryName: String): Call<List<Name>>? {
        return try {
            val names = nameService.getNames("country")
            names
        } catch (e: Exception) {
            null
        }
    }
}