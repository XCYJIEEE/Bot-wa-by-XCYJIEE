package com.xcyjiee.whatsappai.data.local

import android.content.Context

/**
 * Placeholder style profile store.
 * Production recommendation: Room DB with per-contact n-gram and latency features.
 */
class LocalStyleStore(private val context: Context) {
    suspend fun styleProfileFor(contact: String): String {
        return "Friendly, concise, occasional emojis, asks follow-up questions."
    }
}
