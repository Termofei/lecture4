package com.example.lecture4

import retrofit2.Call

class CountryRepository constructor(
    private val countryService: CountryService
) {
    fun getCountries(): Call<List<Country>>? {
        return try {
            val countries = countryService.getCountries()
            countries
        } catch (e: Exception) {
            null
        }
    }

}