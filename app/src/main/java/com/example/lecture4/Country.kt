package com.example.lecture4

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
    var flags: Flag,
    var region: String,
    var population: Int,
    var area: String
)