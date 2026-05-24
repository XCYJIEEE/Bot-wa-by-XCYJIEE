package com.xcyjiee.whatsappai.domain.usecase

import com.xcyjiee.whatsappai.data.repository.ReplyRepository

class GenerateReplyUseCase(private val repository: ReplyRepository) {
    suspend operator fun invoke(contact: String, incoming: String): String {
        return repository.generateSmartReply(contact, incoming)
    }
}
