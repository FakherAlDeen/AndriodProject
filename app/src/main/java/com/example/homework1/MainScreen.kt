package com.example.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val nextButton: Button = findViewById(R.id.NavigateButton)
        nextButton.setOnClickListener {
            val intent = Intent(this@MainScreen, MainActivity::class.java)
            startActivity(intent)
        }

        val addSongButton: Button = findViewById(R.id.AddSong)
        addSongButton.setOnClickListener {
            val intent = Intent(this@MainScreen, AddSongActivity::class.java)
            startActivity(intent)
        }

        val favoriteBtn: Button = findViewById(R.id.Favorite)
        favoriteBtn.setOnClickListener {
            val intent = Intent(this@MainScreen, FavoriteSongsActivity::class.java)
            startActivity(intent)
        }

    }
}