package com.myapp.geogify.presentation.common

object HookInfo {
    const val SELF_EXPLAINED_TEXT = """
Arquitectura
- Usamos MVVM + Clean Architecture. La UI habla con ViewModels, los casos de uso viven en domain y el acceso a datos va por repositorios. Interfaz de dominio real: CountryRepository.

Preferencias
- Guardamos el último país visitado en SharedPreferences (UserPreferences). Al abrir la app, si existe, navegamos a su detalle y luego limpiamos el valor para evitar bucles.

Búsqueda
- Estrategia client-side: filtramos sobre la lista cargada (por nombre/código) en el SearchTab sin llamadas extra a la API.
""";
}
