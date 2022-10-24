package com.app.marvelcharacters.models

import android.content.Context
import androidx.room.*

@Database(entities = [Results::class], version = 1)
@TypeConverters(ObjectConverter::class, ObjectConverter1::class, ObjectConverter2::class)
abstract class RoomDb : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getDatabase(context: Context): RoomDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): RoomDb {
            return Room.databaseBuilder(
                context.applicationContext,
                RoomDb::class.java,
                "notes_database"
            )
                .build()
        }
    }

}

