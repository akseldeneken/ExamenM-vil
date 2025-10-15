package com.myapp.geogify.domain.model

// Data class representando um país
data class Country(
    val code: String,
    val name: String,
    val flagUrl: String?,
    val capital: String?,
    val region: String,
    val languages: List<String>
) {

}