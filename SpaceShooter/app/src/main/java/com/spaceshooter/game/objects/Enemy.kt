package com.spaceshooter.game.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import kotlin.random.Random

class Enemy(private val screenWidth: Int) {

    var x: Float = Random.nextFloat() * (screenWidth - 80) + 40
    var y: Float = -60f
    val width = 60f
    val height = 50f
    var speed = Random.nextFloat() * 4 + 3
    var isAlive = true
    val points = (speed * 10).toInt()

    private val type = Random.nextInt(3)

    private val bodyPaint = Paint().apply {
        color = when (type) {
            0 -> Color.rgb(255, 60, 60)
            1 -> Color.rgb(255, 180, 0)
            else -> Color.rgb(180, 0, 255)
        }
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val detailPaint = Paint().apply {
        color = when (type) {
            0 -> Color.rgb(200, 30, 30)
            1 -> Color.rgb(200, 140, 0)
            else -> Color.rgb(130, 0, 200)
        }
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val eyePaint = Paint().apply {
        color = Color.rgb(255, 255, 100)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    fun update() {
        y += speed
    }

    fun isOffScreen(screenHeight: Int): Boolean {
        return y > screenHeight + height
    }

    fun draw(canvas: Canvas) {
        when (type) {
            0 -> drawTypeA(canvas)
            1 -> drawTypeB(canvas)
            else -> drawTypeC(canvas)
        }
    }

    private fun drawTypeA(canvas: Canvas) {
        val path = Path()
        path.moveTo(x, y - height / 2)
        path.lineTo(x - width / 2, y + height / 4)
        path.lineTo(x - width / 4, y + height / 2)
        path.lineTo(x + width / 4, y + height / 2)
        path.lineTo(x + width / 2, y + height / 4)
        path.close()
        canvas.drawPath(path, bodyPaint)

        canvas.drawCircle(x - 10f, y, 5f, eyePaint)
        canvas.drawCircle(x + 10f, y, 5f, eyePaint)
    }

    private fun drawTypeB(canvas: Canvas) {
        canvas.drawOval(
            x - width / 2, y - height / 3,
            x + width / 2, y + height / 3,
            bodyPaint
        )
        canvas.drawOval(
            x - width / 3, y - height / 5,
            x + width / 3, y + height / 5,
            detailPaint
        )

        canvas.drawCircle(x - 8f, y - 3f, 4f, eyePaint)
        canvas.drawCircle(x + 8f, y - 3f, 4f, eyePaint)
    }

    private fun drawTypeC(canvas: Canvas) {
        canvas.drawRect(
            x - width / 2, y - height / 2,
            x + width / 2, y + height / 2,
            bodyPaint
        )
        canvas.drawRect(
            x - width / 3, y - height / 3,
            x + width / 3, y + height / 3,
            detailPaint
        )

        canvas.drawCircle(x, y, 6f, eyePaint)
    }
}
