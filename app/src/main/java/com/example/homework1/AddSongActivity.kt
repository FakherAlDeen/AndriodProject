package com.example.homework1

import DBHelper
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddSongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)

        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val lyricsEditText = findViewById<EditText>(R.id.lyricsEditText)
        val favoriteCheckbox = findViewById<CheckBox>(R.id.favoriteCheckbox)
        val addButton = findViewById<Button>(R.id.addButton)

        addButton.setOnClickListener {
            val dbHelper = DBHelper(this)
            val db = dbHelper.writableDatabase

            val title = titleEditText.text.toString()
            val lyrics = lyricsEditText.text.toString()
            val isFavorite = favoriteCheckbox.isChecked

            val values = ContentValues()
            values.put(DBHelper.COLUMN_SONG_NAME, title)
            values.put(DBHelper.COLUMN_SONG_LYRICS, lyrics)
            values.put(DBHelper.COLUMN_FAVORITE, if (isFavorite) 1 else 0)

            db.insert(DBHelper.TABLE_SONGS, null, values)
            val addedSong = dbHelper.getLyricsByTitle(title);
            db.close()
            val intent = Intent(this@AddSongActivity, MainActivity::class.java)
            intent.putExtra("selectedSongId", addedSong.id)
            intent.putExtra("selectedSongTitle", addedSong.title)
            intent.putExtra("selectedSongLyrics", addedSong.lyrics)
            startActivity(intent)
        }

        val ReturnBtn: Button = findViewById(R.id.Return)
        ReturnBtn.setOnClickListener {
            val intent = Intent(this@AddSongActivity, MainScreen::class.java)
            startActivity(intent)
        }
    }
}
