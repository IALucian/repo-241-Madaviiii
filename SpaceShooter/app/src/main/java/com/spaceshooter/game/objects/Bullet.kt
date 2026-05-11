package com.spaceshooter.game.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Bullet(var x: Float, var y: Float) {

    val width = 6f
    val height = 16f
    val speed = 15f
    var isActive = true

    private val bulletPaint = Paint().apply {
        color = Color.rgb(0, 255, 200)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val glowPaint = Paint().apply {
        color = Color.rgb(0, 255, 200)
        alpha = 80
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    fun update() {
        y -= speed
        if (y < -height) {
            isActive = false
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawOval(
            x - width - 2, y - height / 2 - 2,
            x + width + 2, y + height / 2 + 2,
            glowPaint
        )

        canvas.drawOval(
            x - width / 2, y - height / 2,
            x + width / 2, y + height / 2,
            bulletPaint
        )
    }
}
