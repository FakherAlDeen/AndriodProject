package com.example.homework1

import DBHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class FavoriteSongsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_songs)

        val db = DBHelper(this)
        val favoriteSongs = db.getFavoriteSongs()

        // Create an ArrayAdapter to display the list of favorite song titles
        val favoriteSongsAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            favoriteSongs.map { it.title }
        )

        val favoriteSongsListView: ListView = findViewById(R.id.favoriteSongsListView)
        favoriteSongsListView.adapter = favoriteSongsAdapter

        favoriteSongsListView.setOnItemClickListener { _, _, position, _ ->
            val selectedFavoriteSong = favoriteSongs[position]

            // Navigate to MainActivity and select the chosen favorite song
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("selectedSongId", selectedFavoriteSong.id)
            intent.putExtra("selectedSongTitle", selectedFavoriteSong.title)
            intent.putExtra("selectedSongLyrics", selectedFavoriteSong.lyrics)
            startActivity(intent)
        }

        val favoriteBtn: Button = findViewById(R.id.BackToMainMenu)
        favoriteBtn.setOnClickListener {
            val intent = Intent(this@FavoriteSongsActivity, MainScreen::class.java)
            startActivity(intent)
        }
    }
}
