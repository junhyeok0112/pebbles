package com.example.pebbles.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

import com.example.pebbles.data.remote.dto.Todo
import com.google.gson.Gson


@ProvidedTypeConverter
class TodoListTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun listToJson(value: List<Todo>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Todo>? {
        return gson.fromJson(value , Array<Todo>::class.java).toList()
    }
}