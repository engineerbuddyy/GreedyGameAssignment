package com.example.greedygamequoteappassignment.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore("favorites")

class FavoriteManager(private val context: Context) {

    companion object {
        private val FAVORITE_IDS = stringSetPreferencesKey("favorite_ids")
    }


    val favoriteIdsFlow: Flow<Set<String>> =
        context.dataStore.data.map { prefs ->
            prefs[FAVORITE_IDS] ?: emptySet()
        }


    suspend fun toggleFavorite(id: String) {
        context.dataStore.edit { prefs ->
            val current = prefs[FAVORITE_IDS] ?: emptySet()
            prefs[FAVORITE_IDS] =
                if (id in current) current - id else current + id
        }
    }
}
