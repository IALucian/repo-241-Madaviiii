package com.spaceshooter.game.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random

class Explosion(private val x: Float, private val y: Float) {

    private data class Particle(
        var px: Float,
        var py: Float,
        var vx: Float,
        var vy: Float,
        var life: Int,
        var maxLife: Int,
        var size: Float,
        var color: Int
    )

    private val particles = mutableListOf<Particle>()
    var isFinished = false
        private set

    private val paint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    init {
        val colors = intArrayOf(
            Color.rgb(255, 200, 0),
            Color.rgb(255, 100, 0),
            Color.rgb(255, 50, 0),
            Color.rgb(255, 255, 100),
            Color.rgb(255, 150, 50)
        )

        for (i in 0 until 20) {
            val angle = Random.nextFloat() * Math.PI.toFloat() * 2
            val speed = Random.nextFloat() * 6 + 2
            particles.add(
                Particle(
                    px = x,
                    py = y,
                    vx = kotlin.math.cos(angle) * speed,
                    vy = kotlin.math.sin(angle) * speed,
                    life = 0,
                    maxLife = Random.nextInt(15) + 10,
                    size = Random.nextFloat() * 5 + 2,
                    color = colors[Random.nextInt(colors.size)]
                )
            )
        }
    }

    fun update() {
        var allDead = true
        for (p in particles) {
            if (p.life < p.maxLife) {
                p.px += p.vx
                p.py += p.vy
                p.vx *= 0.96f
                p.vy *= 0.96f
                p.life++
                allDead = false
            }
        }
        isFinished = allDead
    }

    fun draw(canvas: Canvas) {
        for (p in particles) {
            if (p.life < p.maxLife) {
                val alpha = ((1f - p.life.toFloat() / p.maxLife) * 255).toInt()
                paint.color = p.color
                paint.alpha = alpha
                val currentSize = p.size * (1f - p.life.toFloat() / p.maxLife)
                canvas.drawCircle(p.px, p.py, currentSize, paint)
            }
        }
    }
}
