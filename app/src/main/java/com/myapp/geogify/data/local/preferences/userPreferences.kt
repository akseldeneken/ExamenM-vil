package com.myapp.geogify.data.local.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    // Contexto de aplicación para crear las SharedPreferences
    @ApplicationContext context: Context,
) {
    // archivo de SharedPreferences donde guardamos los datos del usuario
    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    // Guarda el último país visitado (por su code)
    fun saveLastCountry(code: String) {
        prefs.edit().putString("last_country", code).apply()
    }

    // Lee el último país visitado si si existe
    fun getLastCountry(): String? = prefs.getString("last_country", null)

    // Limpia la preferencia del último país
    fun clearLastCountry() {
        prefs.edit().remove("last_country").apply()
    }
}
