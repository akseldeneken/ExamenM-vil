package com.myapp.geogify.data.local.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext context: Context,
) {
    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveLastCountry(code: String) {
        prefs.edit().putString("last_country", code).apply()
    }

    fun getLastCountry(): String? = prefs.getString("last_country", null)

    fun clearLastCountry() {
        prefs.edit().remove("last_country").apply()
    }
}
