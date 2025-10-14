package com.myapp.geogify.domain.repository

import com.myapp.geogify.domain.model.Country

interface CountryRepository {
    suspend fun getCountryList(): List<Country>
    suspend fun getCountryByCode(code: String): Country
}
