package com.niqr.worker.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.niqr.worker.data.model.WorkPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class StoreWorkPreferences @Inject constructor(@ApplicationContext val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "WorkPreferences")
        val preferencesKey = stringPreferencesKey("work_preferences_key")
    }

    val getPreferences: Flow<WorkPreferences> = context.dataStore.data
        .map { preferences ->
            val value = preferences[preferencesKey]
            if (value != null)
                Json.decodeFromString(value)
            else
                WorkPreferences()
        }

    suspend fun setPreferences(workPreferences: WorkPreferences) {
        context.dataStore.edit { settings ->
            settings[preferencesKey] = Json.encodeToString(workPreferences)
        }
    }
}