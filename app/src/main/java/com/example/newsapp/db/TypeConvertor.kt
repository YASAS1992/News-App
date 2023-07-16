package com.example.newsapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsapp.data.Source
import com.google.gson.Gson

@TypeConverters
class TypeConvertor {

    private val gson = Gson()

    @TypeConverter
    fun fromAnyToString(attribute:Any?) : String{
        if (attribute == null)
            return ""
        return attribute as String
    }

    fun fromStringToAny(attribute:String?):Any{
        if (attribute == null)
            return ""
        return attribute
    }

    @TypeConverter
    fun fromSourceToString(source: Source): String? {
        return gson.toJson(source)
    }

    @TypeConverter
    fun toSourceToSource(json: String): Source {
        return gson.fromJson(json, Source::class.java)
    }
}