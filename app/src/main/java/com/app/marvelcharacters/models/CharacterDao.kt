package com.app.marvelcharacters.models

import androidx.room.*

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun charactersList(): List<Character>?

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insert(results: Character?)

    @Update
    fun update(results: Character?)

    @Insert
    fun delete(results: Character?)
}