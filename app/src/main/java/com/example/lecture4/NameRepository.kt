package com.example.lecture4

import retrofit2.Call

class NameRepository constructor(
    private val nameService: NameService
) {
    fun getNames(name: String): Call<List<Name>>? {
        return try {
            nameService.getNames(name)
        } catch (e: Exception) {
            null
        }
    }
}