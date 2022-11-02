package com.example.pebbles.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.pebbles.data.remote.dto.Todo
import com.google.gson.Gson

@ProvidedTypeConverter
class TodoTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun listToJson(value: Todo): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): Todo {
        return gson.fromJson(value, Todo::class.java)
    }
}