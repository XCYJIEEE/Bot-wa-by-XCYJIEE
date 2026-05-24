package com.xcyjiee.whatsappai.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.xcyjiee.whatsappai.core.AppContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Persistent service that keeps NodeJS runtime alive.
 * Handles cold-start resilience and in-memory queue processing.
 */
class BotForegroundService : Service() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(applicationContext)
        startForeground(11, createNotification())
        scope.launch {
            container.nodeRuntimeManager.startIfNeeded()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, "bot_service")
            .setContentTitle("WhatsApp AI service")
            .setContentText("Running intelligent auto-reply orchestration")
            .setSmallIcon(android.R.drawable.stat_notify_sync)
            .build()
    }
}
