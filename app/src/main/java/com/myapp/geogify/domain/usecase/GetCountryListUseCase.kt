package com.myapp.geogify.domain.usecase

import com.myapp.geogify.domain.common.Result
import com.myapp.geogify.domain.model.Country
import com.myapp.geogify.domain.repository.CountryRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCountryListUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    operator fun invoke(): Flow<Result<List<Country>>> = flow {
        try {
            emit(Result.Loading)
            val countries = repository.getCountryList()
            emit(Result.Success(countries))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown Error"))
        }
    }
}