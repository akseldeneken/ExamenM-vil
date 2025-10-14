package com.myapp.geogify.domain.usecase

import com.myapp.geogify.domain.common.Result
import com.myapp.geogify.domain.model.Country
import com.myapp.geogify.domain.repository.CountryRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCountryUseCase @Inject constructor(
    private val repository: CountryRepository,
) {
    // code: ISO code or internal identifier
    operator fun invoke(code: String): Flow<Result<Country>> = flow {
        try {
            emit(Result.Loading)
            // Adjust method name if your repository differs.
            val country = repository.getCountryByCode(code)
            emit(Result.Success(country))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown Error"))
        }
    }
}