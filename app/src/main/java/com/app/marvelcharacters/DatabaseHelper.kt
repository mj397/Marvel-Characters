package com.app.marvelcharacters

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.TextUtils
import org.apache.commons.lang3.SerializationUtils
import java.sql.DriverManager.println

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CHARACTER_COLUMN + " BLOB)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db)
    }

    fun writeResult(result: Results?): Boolean {
        val array = SerializationUtils.serialize(result)
        val db = this.writableDatabase
        val contentValues = ContentValues()
        println("ArrayLength: " + array.size)
        contentValues.put(CHARACTER_COLUMN, array)
        return db.insert(TABLE, null, contentValues) != -1L
    }

    fun readResults(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT " + CHARACTER_COLUMN + " FROM " + TABLE, null)
    }

    fun ifCharacterExits(result: Results): Boolean {
        val name = result.name
        println("CharacterName: $name")
        val cursor = readResults()
        val resultsList = ArrayList<Results>()
        while (cursor.moveToNext()) {
            if (cursor.getBlob(0) != null) {
                val results = SerializationUtils.deserialize<Results>(cursor.getBlob(0))
                resultsList.add(results)
            }
        }
        if (!resultsList.isEmpty()) {
            for (results in resultsList) {
                if (TextUtils.equals(results.name, name)) {
                    return true
                }
            }
        }
        return false
    }

    companion object {
        private const val DATABASE_NAME = "DatabaseHelper"
        private const val CHARACTER_COLUMN = "character_column"
        private const val TABLE = "characters_table"
    }
}