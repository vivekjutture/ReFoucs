package com.personal.refoucs

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.text.get

private val Context.dataStore by preferencesDataStore(name = "refocus_prefs")

object RefocusDataStore {

    private val BLOCK_SHORTS = booleanPreferencesKey("block_shorts")

    fun isBlockingEnabled(context: Context): Boolean {
        return runBlocking {
            context.dataStore.data.first()[BLOCK_SHORTS] ?: true
        }
    }

    suspend fun setBlockingEnabled(context: Context, enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[BLOCK_SHORTS] = enabled
        }
    }
}
