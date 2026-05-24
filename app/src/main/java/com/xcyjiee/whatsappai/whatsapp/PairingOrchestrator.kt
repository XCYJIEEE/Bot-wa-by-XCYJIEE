package com.xcyjiee.whatsappai.whatsapp

import com.xcyjiee.whatsappai.node.NodeRuntimeManager

/**
 * Kotlin façade to request pairing code operations from embedded NodeJS/Baileys.
 */
class PairingOrchestrator(private val nodeRuntimeManager: NodeRuntimeManager) {
    suspend fun requestPairingCode(phoneNumber: String): String {
        nodeRuntimeManager.startIfNeeded()
        // TODO: perform local HTTP call to embedded node server /pairing endpoint
        return "PAIR-CODE-PENDING"
    }
}
