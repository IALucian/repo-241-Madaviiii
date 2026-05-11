package com.spaceshooter.game.engine

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.spaceshooter.game.objects.Bullet
import com.spaceshooter.game.objects.Enemy
import com.spaceshooter.game.objects.Explosion
import com.spaceshooter.game.objects.Player
import com.spaceshooter.game.objects.Star

class GameView(
    context: Context,
    private val onGameOver: (Int) -> Unit
) : SurfaceView(context), SurfaceHolder.Callback {

    private var gameThread: GameThread? = null
    private lateinit var player: Player
    private val enemies = mutableListOf<Enemy>()
    private val bullets = mutableListOf<Bullet>()
    private val stars = mutableListOf<Star>()
    private val explosions = mutableListOf<Explosion>()

    private var score = 0
    private var frameCount = 0
    private var enemySpawnRate = 60
    private var shootCooldown = 0
    private var gameOver = false
    private var screenWidth = 0
    private var screenHeight = 0
    private var initialized = false

    private val scorePaint = Paint().apply {
        color = Color.WHITE
        textSize = 48f
        typeface = Typeface.DEFAULT_BOLD
        isAntiAlias = true
    }

    private val livesPaint = Paint().apply {
        color = Color.rgb(255, 80, 80)
        textSize = 42f
        typeface = Typeface.DEFAULT_BOLD
        isAntiAlias = true
    }

    private val backgroundPaint = Paint().apply {
        color = Color.rgb(5, 5, 20)
        style = Paint.Style.FILL
    }

    init {
        holder.addCallback(this)
        isFocusable = true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        screenWidth = width
        screenHeight = height
        initGame()
        gameThread = GameThread(holder, this)
        gameThread?.running = true
        gameThread?.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, w: Int, h: Int) {
        screenWidth = w
        screenHeight = h
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        gameThread?.running = false
        try {
            gameThread?.join()
        } catch (_: InterruptedException) {
        }
    }

    private fun initGame() {
        if (screenWidth == 0 || screenHeight == 0) return
        player = Player(screenWidth, screenHeight)
        enemies.clear()
        bullets.clear()
        explosions.clear()
        stars.clear()

        for (i in 0 until 80) {
            stars.add(Star(screenWidth, screenHeight))
        }

        score = 0
        frameCount = 0
        gameOver = false
        initialized = true
    }

    fun updateGame() {
        if (!initialized || gameOver) return

        frameCount++
        player.update()

        for (star in stars) {
            star.update()
        }

        if (shootCooldown > 0) shootCooldown--

        if (frameCount % enemySpawnRate == 0) {
            enemies.add(Enemy(screenWidth))
            if (enemySpawnRate > 20) {
                enemySpawnRate = (60 - score / 50).coerceAtLeast(20)
            }
        }

        val bulletsToRemove = mutableListOf<Bullet>()
        for (bullet in bullets) {
            bullet.update()
            if (!bullet.isActive) {
                bulletsToRemove.add(bullet)
            }
        }
        bullets.removeAll(bulletsToRemove.toSet())

        val enemiesToRemove = mutableListOf<Enemy>()
        for (enemy in enemies) {
            enemy.update()
            if (enemy.isOffScreen(screenHeight)) {
                enemiesToRemove.add(enemy)
                continue
            }

            if (enemy.isAlive) {
                for (bullet in bullets) {
                    if (bullet.isActive && checkCollision(
                            bullet.x, bullet.y, bullet.width, bullet.height,
                            enemy.x, enemy.y, enemy.width, enemy.height
                        )
                    ) {
                        bullet.isActive = false
                        enemy.isAlive = false
                        score += enemy.points
                        explosions.add(Explosion(enemy.x, enemy.y))
                        enemiesToRemove.add(enemy)
                        break
                    }
                }
            }

            if (enemy.isAlive && checkCollision(
                    player.x, player.y, player.width, player.height,
                    enemy.x, enemy.y, enemy.width, enemy.height
                )
            ) {
                enemy.isAlive = false
                enemiesToRemove.add(enemy)
                explosions.add(Explosion(enemy.x, enemy.y))
                player.hit()

                if (player.lives <= 0) {
                    gameOver = true
                    explosions.add(Explosion(player.x, player.y))
                    post { onGameOver(score) }
                    return
                }
            }
        }
        enemies.removeAll(enemiesToRemove.toSet())

        val explosionsToRemove = mutableListOf<Explosion>()
        for (explosion in explosions) {
            explosion.update()
            if (explosion.isFinished) {
                explosionsToRemove.add(explosion)
            }
        }
        explosions.removeAll(explosionsToRemove.toSet())
    }

    fun drawGame(canvas: Canvas) {
        if (!initialized) return

        canvas.drawRect(0f, 0f, screenWidth.toFloat(), screenHeight.toFloat(), backgroundPaint)

        for (star in stars) {
            star.draw(canvas)
        }

        for (bullet in bullets) {
            if (bullet.isActive) bullet.draw(canvas)
        }

        for (enemy in enemies) {
            if (enemy.isAlive) enemy.draw(canvas)
        }

        for (explosion in explosions) {
            explosion.draw(canvas)
        }

        if (!gameOver) {
            player.draw(canvas)
        }

        canvas.drawText("Puntos: $score", 30f, 80f, scorePaint)

        val livesText = "♥".repeat(player.lives)
        canvas.drawText(livesText, screenWidth - 180f, 80f, livesPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (gameOver || !initialized) return true

        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                player.moveTo(event.x)

                if (shootCooldown == 0) {
                    bullets.add(Bullet(player.x, player.y - player.height / 2))
                    shootCooldown = 10
                }
            }
        }
        return true
    }

    private fun checkCollision(
        x1: Float, y1: Float, w1: Float, h1: Float,
        x2: Float, y2: Float, w2: Float, h2: Float
    ): Boolean {
        return kotlin.math.abs(x1 - x2) < (w1 + w2) / 2 &&
                kotlin.math.abs(y1 - y2) < (h1 + h2) / 2
    }

    fun pause() {
        gameThread?.running = false
        try {
            gameThread?.join()
        } catch (_: InterruptedException) {
        }
    }

    fun resume() {
        if (initialized && holder.surface.isValid) {
            gameThread = GameThread(holder, this)
            gameThread?.running = true
            gameThread?.start()
        }
    }
}
