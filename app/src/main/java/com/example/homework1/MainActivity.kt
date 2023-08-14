package com.example.homework1

import DBHelper
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DBHelper(this)
        val spinnerVal: Spinner = findViewById(R.id.spinnerFaye3)
        val textViewsContainer: TextView = findViewById(R.id.Lyrics)
        val favoriteButton: Button = findViewById(R.id.favoriteButton)
        val backButton: Button = findViewById(R.id.BackToMain)
        var currentSong: Song? = null



        val songTitles = db.getAllSongTitles()
        val optionsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songTitles)
        spinnerVal.adapter = optionsAdapter
        val selectedSongId = intent.getIntExtra("selectedSongId", -1)
        if (selectedSongId != -1) {
            val selectedSongTitle = intent.getStringExtra("selectedSongTitle")
            val selectedSongLyrics = intent.getStringExtra("selectedSongLyrics")
            val songTitles = db.getAllSongTitles()
            val selectedSongIndex = songTitles.indexOf(selectedSongTitle)
            if (selectedSongIndex != -1) {
                spinnerVal.setSelection(selectedSongIndex)
                textViewsContainer.text = selectedSongLyrics
            }
        }
        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedSongTitle = songTitles[p2]
                currentSong = db.getLyricsByTitle(selectedSongTitle)
                if (currentSong!!.favorite == false){
                    favoriteButton.text = "Add To favorite"
                }else {
                    favoriteButton.text = "Delete From Favorite"
                }
                val stringBuilder = StringBuilder()
                for (line in currentSong!!.lyrics.split("\n")) {
                    stringBuilder.append(line).append("\n")
                }
                textViewsContainer.text = stringBuilder.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // ...
            }
        }

        favoriteButton.setOnClickListener {
            if (currentSong != null) {
                currentSong = db.getLyricsByTitle(currentSong!!.title);
                if (currentSong!!.favorite == false) {
                    db.addToFavorites(currentSong!!.id, 1)
                    favoriteButton.text = "Delete From Favorite"
                    Toast.makeText(this, "Added to favorites!", Toast.LENGTH_SHORT).show()
                } else {
                    db.addToFavorites(currentSong!!.id, 0)
                    favoriteButton.text = "Add To favorite"
                    Toast.makeText(this, "Deleted from favorites!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val deleteSongButton: Button = findViewById(R.id.DeleteSong)
        deleteSongButton.setOnClickListener {
            val selectedSongTitle = spinnerVal.selectedItem.toString()
            val selectedSongId = db.getSongIdByTitle(selectedSongTitle)

            // Delete the selected song from the database
            db.deleteSongById(selectedSongId)

            // Update the spinner
            val updatedSongTitles = db.getAllSongTitles()
            val updatedAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, updatedSongTitles)
            spinnerVal.adapter = updatedAdapter

            // Clear the lyrics TextView
            textViewsContainer.text = ""

            // Show a toast to indicate that the song has been deleted
            Toast.makeText(this, "Song deleted: $selectedSongTitle", Toast.LENGTH_SHORT).show()
        }

        backButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MainScreen::class.java)
            startActivity(intent)
        }
    }

    var flagBGColor = false;
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
     val inflater = menuInflater
     inflater.inflate(R.menu.menu_dark, menu)
     return super.onCreateOptionsMenu(menu)
     }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val textViewsContainer: TextView = findViewById(R.id.Lyrics)
        when (item.itemId) {
            R.id.Dark -> {
                if (!flagBGColor){
                    flagBGColor = true
                    val rootView = findViewById<View>(android.R.id.content)
                    rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.app_background_color_new))
                    ViewCompat.setBackgroundTintList(textViewsContainer , ContextCompat.getColorStateList(this, R.color.white))
                    return true
                }else {
                    flagBGColor = false;
                    val rootView = findViewById<View>(android.R.id.content)
                    rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                    ViewCompat.setBackgroundTintList(textViewsContainer , ContextCompat.getColorStateList(this, R.color.Grey))
                    return true
                }
            }
            R.id.FSize ->{
                var dialogVar = DialogFragmentText()
                dialogVar.show(supportFragmentManager, "Size Dialog")
            }
            // Handle other menu items similarly
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    public fun ChangeSizeOfText(size: Float){
        val TVLyrics = findViewById<TextView>(R.id.Lyrics);
        TVLyrics.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
    }

}