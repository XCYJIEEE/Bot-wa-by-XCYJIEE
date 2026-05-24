package com.xcyjiee.whatsappai.ai

import com.xcyjiee.whatsappai.data.local.LocalStyleStore

class StyleImitationEngine(private val localStyleStore: LocalStyleStore) {
    suspend fun stylePrompt(contact: String): String {
        return localStyleStore.styleProfileFor(contact)
    }
}
