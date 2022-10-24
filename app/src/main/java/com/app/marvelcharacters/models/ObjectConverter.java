package com.app.marvelcharacters.models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ObjectConverter {

    @TypeConverter
    public static List<Character> storedStringToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Character>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStoredString(List<Character> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }



    @TypeConverter
    public static List<Thumbnail> storedString(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Thumbnail>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStored(List<Thumbnail> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }

}
