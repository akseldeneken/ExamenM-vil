package com.myapp.geogify.data.remote.api

import com.myapp.geogify.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApi {

    // Lista de países limitada a los campos que vamos a usar en UI.
    @GET("all")
    suspend fun getAll(
        @Query("fields") fields: String =
            "name,cca2,cca3,flags,capital,region,languages"
    ): List<CountryDto>

    //  añade campos extra de detalle.
    @GET("alpha/{code}")
    suspend fun getByCode(
        @Path("code") code: String,
        @Query("fields") fields: String =
            "name,cca2,cca3,flags,capital,region,languages,subregion,population"
    ): CountryDto
}
