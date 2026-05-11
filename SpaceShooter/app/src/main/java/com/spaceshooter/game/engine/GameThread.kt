package com.spaceshooter.game.engine

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameThread(
    private val surfaceHolder: SurfaceHolder,
    private val gameView: GameView
) : Thread() {

    var running = false
    private val targetFPS = 60L
    private val targetTime = 1000L / targetFPS

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long

        while (running) {
            startTime = System.nanoTime()
            var canvas: Canvas? = null

            try {
                canvas = surfaceHolder.lockCanvas()
                if (canvas != null) {
                    synchronized(surfaceHolder) {
                        gameView.updateGame()
                        gameView.drawGame(canvas)
                    }
                }
            } catch (_: Exception) {
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (_: Exception) {
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1_000_000
            waitTime = targetTime - timeMillis

            if (waitTime > 0) {
                try {
                    sleep(waitTime)
                } catch (_: InterruptedException) {
                }
            }
        }
    }
}
