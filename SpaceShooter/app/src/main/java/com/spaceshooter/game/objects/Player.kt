package com.spaceshooter.game.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

class Player(private val screenWidth: Int, private val screenHeight: Int) {

    var x: Float = screenWidth / 2f
    var y: Float = screenHeight - 200f
    val width = 80f
    val height = 100f
    var lives = 3
    var isInvincible = false
    private var invincibleTimer = 0

    private val bodyPaint = Paint().apply {
        color = Color.rgb(0, 200, 255)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val wingPaint = Paint().apply {
        color = Color.rgb(0, 150, 220)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val cockpitPaint = Paint().apply {
        color = Color.rgb(200, 230, 255)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val enginePaint = Paint().apply {
        color = Color.rgb(255, 150, 0)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    fun moveTo(targetX: Float) {
        x = targetX.coerceIn(width / 2, screenWidth - width / 2)
    }

    fun update() {
        if (isInvincible) {
            invincibleTimer++
            if (invincibleTimer > 120) {
                isInvincible = false
                invincibleTimer = 0
            }
        }
    }

    fun hit() {
        if (!isInvincible) {
            lives--
            isInvincible = true
            invincibleTimer = 0
        }
    }

    fun draw(canvas: Canvas) {
        if (isInvincible && invincibleTimer % 10 < 5) return

        val path = Path()
        path.moveTo(x, y - height / 2)
        path.lineTo(x - width / 4, y + height / 4)
        path.lineTo(x + width / 4, y + height / 4)
        path.close()
        canvas.drawPath(path, bodyPaint)

        val leftWing = Path()
        leftWing.moveTo(x - width / 4, y)
        leftWing.lineTo(x - width / 2, y + height / 3)
        leftWing.lineTo(x - width / 6, y + height / 4)
        leftWing.close()
        canvas.drawPath(leftWing, wingPaint)

        val rightWing = Path()
        rightWing.moveTo(x + width / 4, y)
        rightWing.lineTo(x + width / 2, y + height / 3)
        rightWing.lineTo(x + width / 6, y + height / 4)
        rightWing.close()
        canvas.drawPath(rightWing, wingPaint)

        canvas.drawCircle(x, y - height / 6, 10f, cockpitPaint)

        canvas.drawRect(
            x - 8f, y + height / 4,
            x + 8f, y + height / 4 + 15f,
            enginePaint
        )
    }
}
