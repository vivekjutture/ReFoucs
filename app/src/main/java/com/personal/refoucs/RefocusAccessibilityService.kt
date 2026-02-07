package com.personal.refoucs


import android.accessibilityservice.AccessibilityService
import android.os.SystemClock
import android.view.accessibility.AccessibilityEvent

class RefocusAccessibilityService : AccessibilityService() {

    private var lastClickTime = 0L
    private var lastBackTime = 0L
    private var pendingVerification = false

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        if (event.packageName != "com.google.android.youtube") return
        if (!RefocusDataStore.isBlockingEnabled(this)) return

        when (event.eventType) {

            // Track explicit user intent
            AccessibilityEvent.TYPE_VIEW_CLICKED -> {
                lastClickTime = SystemClock.uptimeMillis()
            }

            // Navigation boundary
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                val now = SystemClock.uptimeMillis()

                // Must be immediately after a click
                if (now - lastClickTime > 600) return

                // Avoid loops
                if (now - lastBackTime < 1000) return

                // Tentatively exit and verify
                pendingVerification = true
                performGlobalAction(GLOBAL_ACTION_BACK)
                lastBackTime = now
            }

            // Verify result of BACK
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                if (!pendingVerification) return

                // If we are back to feed quickly, this was Shorts
                pendingVerification = false
                // Do nothing further — Shorts successfully blocked
            }
        }
    }

    override fun onInterrupt() {}
}
