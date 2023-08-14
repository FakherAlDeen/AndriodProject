import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.homework1.Song

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "songs.db"
        private const val DATABASE_VERSION = 1

        // Table name and column names
        const val TABLE_SONGS = "songs"
        const val COLUMN_ID = "id"
        const val COLUMN_SONG_NAME = "song_name"
        const val COLUMN_SONG_LYRICS = "song_lyrics"
        const val COLUMN_FAVORITE = "favorite"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_SONGS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_SONG_NAME TEXT," +
                "$COLUMN_SONG_LYRICS TEXT," +
                "$COLUMN_FAVORITE INTEGER DEFAULT 0)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SONGS")
        onCreate(db)
    }
    fun getAllSongTitles(): List<String> {
        val songTitles = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT ${COLUMN_SONG_NAME} FROM ${TABLE_SONGS}", null)
        while (cursor.moveToNext()) {
            val songTitle = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SONG_NAME))
            songTitles.add(songTitle)
        }
        cursor.close()
        return songTitles
    }

    fun getSongIdByTitle(songTitle: String): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT ${COLUMN_ID} FROM ${TABLE_SONGS} WHERE ${COLUMN_SONG_NAME} = ?", arrayOf(songTitle))
        cursor.moveToFirst()
        val songId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        cursor.close()
        return songId
    }

    fun getSongById(songId: Int): Song {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${TABLE_SONGS} WHERE ${COLUMN_ID} = ?", arrayOf(songId.toString()))
        cursor.moveToFirst()
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SONG_NAME))
        val lyrics = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SONG_LYRICS))
        val favorite = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FAVORITE)) == 1
        cursor.close()
        return Song(songId, title, lyrics, favorite)
    }

    @SuppressLint("Range")
    fun getLyricsByTitle(title: String): Song {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_SONGS WHERE $COLUMN_SONG_NAME = ?"
        val cursor = db.rawQuery(query, arrayOf(title))
        var song = Song(0, "", "", false) // Initialize an empty Song object

        if (cursor.moveToFirst()) {
            song.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            song.title = cursor.getString(cursor.getColumnIndex(COLUMN_SONG_NAME))
            song.lyrics = cursor.getString(cursor.getColumnIndex(COLUMN_SONG_LYRICS))
            song.favorite = cursor.getInt(cursor.getColumnIndex(COLUMN_FAVORITE)) == 1
        }

        cursor.close()
        db.close()
        return song
    }

    fun addToFavorites(songId: Int, flag: Int) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_FAVORITE, flag)
        db.update(TABLE_SONGS, contentValues, "$COLUMN_ID = ?", arrayOf(songId.toString()))
        db.close()
    }

    fun deleteSongById(songId: Int) {
        val db = writableDatabase
        db.delete(TABLE_SONGS, "$COLUMN_ID = ?", arrayOf(songId.toString()))
        db.close()
    }

    fun getFavoriteSongs(): List<Song> {
        val favoriteSongs = mutableListOf<Song>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_SONGS WHERE $COLUMN_FAVORITE = 1", null)

        while (cursor.moveToNext()) {
            val songId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SONG_NAME))
            val lyrics = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SONG_LYRICS))
            val favorite = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FAVORITE)) == 1 // Retrieve favorite value and convert to Boolean
            favoriteSongs.add(Song(songId, title, lyrics, favorite))
        }

        cursor.close()
        return favoriteSongs
    }



}
