package com.app.marvelcharacters.models

import androidx.room.TypeConverter
import com.app.marvelcharacters.models.Results
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ObjectConverter {
    @TypeConverter
    @JvmStatic
    fun stringToObject(data: String?): Results? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<Results?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun objectToString(myObjects: Results?): String {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

}