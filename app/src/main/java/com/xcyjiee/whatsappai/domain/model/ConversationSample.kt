package com.xcyjiee.whatsappai.domain.model

data class ConversationSample(
    val contactJid: String,
    val incomingText: String,
    val userReply: String,
    val timestampEpochMs: Long
)
