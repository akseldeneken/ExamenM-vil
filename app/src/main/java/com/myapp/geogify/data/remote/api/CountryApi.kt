package com.myapp.geogify.data.remote.api

import com.myapp.geogify.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApi {


    @GET("all")
    suspend fun getAll(
        @Query("fields") fields: String = "name,cca2,cca3,flags,capital,region,languages"
    ): List<CountryDto>


    @GET("alpha/{code}")
    suspend fun getByCode(
        @Path("code") code: String,
        @Query("fields") fields: String = "name,cca2,cca3,flags,capital,region,languages"
    ): List<CountryDto>
}
