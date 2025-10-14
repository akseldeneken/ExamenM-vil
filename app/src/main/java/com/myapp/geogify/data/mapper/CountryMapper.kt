package com.myapp.geogify.data.mapper

import com.myapp.geogify.data.remote.dto.CountryDto
import com.myapp.geogify.domain.model.Country

fun CountryDto.toDomain(): Country =
    Country(
        code = (cca3?.takeIf { it.isNotBlank() } ?: cca2.orEmpty()).uppercase(),
        name = (name?.common ?: name?.official).orEmpty()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
        flagSvg = flags?.svg,
        capital = capital?.firstOrNull(),
        region = region.orEmpty(),
        languages = languages?.values?.filter { it.isNotBlank() }?.distinct()?.sorted().orEmpty()
    )

fun List<CountryDto>.toDomain(): List<Country> = map { it.toDomain() }
