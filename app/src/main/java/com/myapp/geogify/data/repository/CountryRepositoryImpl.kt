import com.myapp.geogify.data.mapper.toDomain
import com.myapp.geogify.data.remote.api.CountryApi
import com.myapp.geogify.domain.model.Country
import com.myapp.geogify.domain.repository.CountryRepository
import javax.inject.Inject
import javax.inject.Singleton

// Se inyecta la API con Hilt
@Singleton
class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi
) : CountryRepository {

    // Trae lista desde la API y la mapea
    override suspend fun getCountryList(): List<Country> {
        val dtos = api.getAll(fields = "name,cca2,cca3,flags,region,capital,languages")
        return dtos.map { it.toDomain() }
    }

    // Trae un país por código y lo convierte al modelo de dominio
    override suspend fun getCountryByCode(code: String): Country {
        val dto = api.getByCode(
            code = code,
            fields = "name,cca2,cca3,flags,region,capital,subregion,languages,population"
        )
        return dto.toDomain()
    }
}
