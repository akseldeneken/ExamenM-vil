package com.myapp.geogify.data.remote.dto

data class CountryListDto(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<CountryDto>
) {
    companion object {
        fun fromRawList(list: List<CountryDto>): CountryListDto =
            CountryListDto(
                count = list.size,
                results = list
            )
    }
}
