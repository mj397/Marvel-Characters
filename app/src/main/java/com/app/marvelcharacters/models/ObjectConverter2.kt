package com.app.marvelcharacters.models

import androidx.room.TypeConverter
import com.app.marvelcharacters.models.Results
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ObjectConverter2 {

    @TypeConverter
    @JvmStatic
    fun stringToObject2(data: String?): Extras? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<Extras?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun objectToString1(myObjects: Extras?): String {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

}