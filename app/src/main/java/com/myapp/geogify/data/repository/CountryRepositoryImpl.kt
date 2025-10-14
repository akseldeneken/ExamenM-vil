package com.myapp.geogify.data.repository

import com.myapp.geogify.data.mapper.toDomain
import com.myapp.geogify.data.remote.api.CountryApi
import com.myapp.geogify.domain.model.Country
import com.myapp.geogify.domain.repository.CountryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi
) : CountryRepository {

    override suspend fun getCountryList(): List<Country> {
        val dtos = api.getAll(fields = "name,cca2,cca3,flags,region,capital")
        return dtos.map { it.toDomain() }
    }

    override suspend fun getCountryByCode(code: String): Country {
        val dtoList = api.getByCode(code = code, fields = "name,cca2,cca3,flags,region,capital,subregion,languages,population")
        return dtoList.first().toDomain()
    }
}