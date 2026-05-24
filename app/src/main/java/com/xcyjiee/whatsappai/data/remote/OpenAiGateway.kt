package com.xcyjiee.whatsappai.data.remote

/**
 * Thin API client wrapper for OpenAI chat responses.
 * Keep API key in Android Keystore-backed encrypted storage.
 */
class OpenAiGateway {
    suspend fun generateReply(system: String, user: String): String {
        // TODO: implement HTTPS call to OpenAI Responses API.
        return "Got it — I will circle back in a bit."
    }
}
