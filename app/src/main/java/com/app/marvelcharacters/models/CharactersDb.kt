//package com.app.marvelcharacters.models
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import androidx.room.Room
//import androidx.room.TypeConverters
//
//@Database(entities = [Character::class], exportSchema = true, version = 1)
//@TypeConverters(ObjectConverter::class)
//abstract class CharactersDb : RoomDatabase() {
//    abstract fun charactersDb(): CharacterDao
//
//    companion object {
//        private const val DB_NAME = "characters_db"
//        @Volatile
//        private var instance: CharactersDb? = null
//        fun getInstance(context: Context): CharactersDb {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            if (instance == null) {
//                synchronized(this) {
//                    // Pass the database to the INSTANCE
//                    instance = buildDatabase(context)
//                }
//            }
//            // Return database.
//            return instance!!
//        }
//
//        private fun buildDatabase(context: Context): CharactersDb {
//            return Room.databaseBuilder(
//                context.applicationContext,
//                CharactersDb::class.java,
//                DB_NAME
//            )
//                .build()
//        }
//    }
//
//
//}