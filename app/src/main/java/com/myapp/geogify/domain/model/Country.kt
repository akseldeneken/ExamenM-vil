package com.myapp.geogify.domain.model

data class Country(
    val code: String,
    val name: String,
    val flagSvg: String?,
    val capital: String?,
    val region: String,
    val languages: List<String>
) {
    companion object {
        fun getMockData(): List<Country> =
            listOf(
                Country(
                    code = "MEX",
                    name = "Mexico",
                    flagSvg = "https://restcountries.com/data/mex.svg",
                    capital = "Mexico City",
                    region = "Americas",
                    languages = listOf("Spanish")
                ),
                Country(
                    code = "CAN",
                    name = "Canada",
                    flagSvg = "https://restcountries.com/data/can.svg",
                    capital = "Ottawa",
                    region = "Americas",
                    languages = listOf("English", "French")
                ),
                Country(
                    code = "FRA",
                    name = "France",
                    flagSvg = "https://restcountries.com/data/fra.svg",
                    capital = "Paris",
                    region = "Europe",
                    languages = listOf("French")
                )
            )
    }
}