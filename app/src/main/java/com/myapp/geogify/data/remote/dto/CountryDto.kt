package com.myapp.geogify.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name") val name: NameDto? = null,
    @SerializedName("cca2") val cca2: String? = null,
    @SerializedName("cca3") val cca3: String? = null,
    @SerializedName("flags") val flags: FlagsDto? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("subregion") val subregion: String? = null,
    @SerializedName("capital") val capital: List<String>? = null,
    @SerializedName("languages") val languages: Map<String, String>? = null,
    @SerializedName("population") val population: Long? = null
) {
    data class NameDto(
        @SerializedName("common") val common: String? = null,
        @SerializedName("official") val official: String? = null
    )
    data class FlagsDto(
        @SerializedName("png") val png: String? = null,
        @SerializedName("svg") val svg: String? = null,
        @SerializedName("alt") val alt: String? = null
    )
}
