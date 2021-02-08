package com.example.breakingbadapp.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

internal class DatabaseTypeConverters {

    private val gson = Gson()

    @TypeConverter
    fun stringToStringList(data: String?): List<String?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<String?>?): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun someObjectListToInt(someObjects: List<Int?>?): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToIntList(data: String?): List<Int?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Int?>?>() {}.type
        return gson.fromJson(data, listType)
    }
}