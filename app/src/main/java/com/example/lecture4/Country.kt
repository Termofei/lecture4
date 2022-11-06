package com.example.lecture4
import android.graphics.Region

data class Country(
    var name: String,
    var capital: String,
    var flags: Flag
)
data class Flag (
    var svg: String,
    var png: String
        )

data class Name(
    var name: String,
    var capital: String,
    var region: String,
    var population: Int,
    var area: Int,
    var flags: Flag
)