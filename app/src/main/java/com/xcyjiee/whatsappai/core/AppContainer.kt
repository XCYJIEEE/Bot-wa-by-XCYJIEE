package com.xcyjiee.whatsappai.core

import android.content.Context
import com.xcyjiee.whatsappai.ai.StyleImitationEngine
import com.xcyjiee.whatsappai.data.local.LocalStyleStore
import com.xcyjiee.whatsappai.data.remote.OpenAiGateway
import com.xcyjiee.whatsappai.data.repository.ReplyRepository
import com.xcyjiee.whatsappai.domain.usecase.GenerateReplyUseCase
import com.xcyjiee.whatsappai.node.NodeRuntimeManager
import com.xcyjiee.whatsappai.whatsapp.PairingOrchestrator

/**
 * Central dependency container.
 * In production, replace with Hilt/Dagger modules.
 */
class AppContainer(context: Context) {
    private val localStyleStore = LocalStyleStore(context)
    private val openAiGateway = OpenAiGateway()
    private val styleEngine = StyleImitationEngine(localStyleStore)
    private val replyRepository = ReplyRepository(openAiGateway, styleEngine)

    val generateReplyUseCase = GenerateReplyUseCase(replyRepository)
    val nodeRuntimeManager = NodeRuntimeManager(context)
    val pairingOrchestrator = PairingOrchestrator(nodeRuntimeManager)
}
