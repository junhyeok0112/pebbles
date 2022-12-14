package com.todaypebble.pebbles.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.todaypebble.pebbles.data.remote.dto.Week
import com.google.gson.Gson

@ProvidedTypeConverter
class WeeksTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun listToJson(value: Week): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): Week {
        return gson.fromJson(value, Week::class.java)
    }
}