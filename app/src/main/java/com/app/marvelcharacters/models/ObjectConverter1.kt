package com.app.marvelcharacters.models

import androidx.room.TypeConverter
import com.app.marvelcharacters.models.Results
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

 object ObjectConverter1 {

    @TypeConverter
    @JvmStatic
    fun stringToObject1(data: String?): Thumbnail? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<Thumbnail?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun objectToString1(myObjects: Thumbnail?): String {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

}