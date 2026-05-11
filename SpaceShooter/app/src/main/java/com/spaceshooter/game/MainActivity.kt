package com.spaceshooter.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleText = findViewById<TextView>(R.id.titleText)
        val subtitleText = findViewById<TextView>(R.id.subtitleText)
        val playButton = findViewById<Button>(R.id.playButton)

        titleText.text = getString(R.string.app_name)
        subtitleText.text = getString(R.string.subtitle)

        playButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
