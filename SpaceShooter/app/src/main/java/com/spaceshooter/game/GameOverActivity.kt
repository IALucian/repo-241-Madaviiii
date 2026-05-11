package com.spaceshooter.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val score = intent.getIntExtra("SCORE", 0)

        val gameOverText = findViewById<TextView>(R.id.gameOverText)
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val playAgainButton = findViewById<Button>(R.id.playAgainButton)
        val menuButton = findViewById<Button>(R.id.menuButton)

        gameOverText.text = getString(R.string.game_over)
        scoreText.text = getString(R.string.final_score, score)

        playAgainButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            finish()
        }

        menuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
