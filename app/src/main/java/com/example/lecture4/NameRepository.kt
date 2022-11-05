package com.example.lecture4

import retrofit2.Call

class NameRepository constructor(
    private val nameService: NameService
) {
    fun getNames(): Call<List<Name>>? {
        return try {
            val names = nameService.getNames()
            names
        } catch (e: Exception) {
            null
        }
    }
}