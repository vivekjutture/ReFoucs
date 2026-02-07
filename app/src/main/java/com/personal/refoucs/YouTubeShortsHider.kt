package com.personal.refoucs


import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityNodeInfo

object YouTubeShortsHider {

    fun handle(root: AccessibilityNodeInfo, service: AccessibilityService) {
        blockShortsShelf(root)
        blockShortsPlayer(root, service)
    }

    /**
     * Removes Shorts shelves/cards from feeds
     */
    private fun blockShortsShelf(node: AccessibilityNodeInfo?) {
        if (node == null) return

        val viewId = node.viewIdResourceName ?: ""

        // Known Shorts-related IDs (YouTube changes these often)
        if (
            viewId.contains("shorts", ignoreCase = true) ||
            viewId.contains("reel", ignoreCase = true)
        ) {
            val parent = node.parent
            if (parent != null && parent.isDismissable()) {
                parent.performAction(AccessibilityNodeInfo.ACTION_DISMISS)
                return
            }
        }

        for (i in 0 until node.childCount) {
            blockShortsShelf(node.getChild(i))
        }
    }

    /**
     * Detects vertical Shorts player and exits immediately
     */
    private fun blockShortsPlayer(
        node: AccessibilityNodeInfo?,
        service: AccessibilityService
    ) {
        if (node == null) return

        // Shorts player is usually:
        // - Full screen
        // - Scrollable
        // - Only 1 main child
        // - Uses FrameLayout / ComposeView
        val className = node.className?.toString() ?: ""

        if (
            node.isScrollable &&
            node.childCount == 1 &&
            (
                    className.contains("FrameLayout") ||
                            className.contains("Compose")
                    )
        ) {
            // Exit Shorts immediately
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
            return
        }

        for (i in 0 until node.childCount) {
            blockShortsPlayer(node.getChild(i), service)
        }
    }

    /**
     * Helper to check dismiss capability safely
     */
    private fun AccessibilityNodeInfo.isDismissable(): Boolean {
        return actionList.any { it.id == AccessibilityNodeInfo.ACTION_DISMISS }
    }
}


