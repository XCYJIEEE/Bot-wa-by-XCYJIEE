package com.xcyjiee.whatsappai.data.repository

import com.xcyjiee.whatsappai.ai.StyleImitationEngine
import com.xcyjiee.whatsappai.data.remote.OpenAiGateway

class ReplyRepository(
    private val openAiGateway: OpenAiGateway,
    private val styleImitationEngine: StyleImitationEngine
) {
    suspend fun generateSmartReply(contact: String, incoming: String): String {
        val style = styleImitationEngine.stylePrompt(contact)
        val systemPrompt = """
            You are a relationship-focused messaging copilot.
            Reply naturally, avoid robotic structure, mirror user style:
            $style
        """.trimIndent()
        return openAiGateway.generateReply(systemPrompt, incoming)
    }
}
