package com.spaceshooter.game.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random

class Star(private val screenWidth: Int, private val screenHeight: Int) {

    var x: Float = Random.nextFloat() * screenWidth
    var y: Float = Random.nextFloat() * screenHeight
    private val size = Random.nextFloat() * 3 + 1
    private val speed = size * 0.8f
    private val brightness = (Random.nextFloat() * 155 + 100).toInt()

    private val starPaint = Paint().apply {
        color = Color.rgb(brightness, brightness, brightness)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    fun update() {
        y += speed
        if (y > screenHeight) {
            y = 0f
            x = Random.nextFloat() * screenWidth
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(x, y, size, starPaint)
    }
}
