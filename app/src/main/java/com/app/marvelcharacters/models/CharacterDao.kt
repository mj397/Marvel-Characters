package com.app.marvelcharacters.models

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun charactersList(): List<Results>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(results: Results?)


    @Query("SELECT COUNT() FROM characters WHERE characterId = :id")
    fun count(id: String?): Int

//    @Query("SELECT * FROM characters WHERE characterId = :id")
//    fun checkCharacter(id: String): LiveData<Results>
}