package com.example.shaadi.services.room

import androidx.room.TypeConverter
import com.example.shaadi.models.Info
import com.example.shaadi.models.Results
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromResult(results: ArrayList<Results>) : String {
        return Gson().toJson(results)
    }

    @TypeConverter
    fun toResult(result: String) : ArrayList<Results> {
        return Gson().fromJson(result, object : TypeToken<ArrayList<Results>>(){}.type)
    }

    @TypeConverter
    fun fromInfo(info: Info) : String {
        return Gson().toJson(info)
    }

    @TypeConverter
    fun toInfo(result: String) : Info {
        return Gson().fromJson(result, object : TypeToken<Info>(){}.type)
    }
}