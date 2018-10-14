package com.trasimus.menu.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trasimus.menu.objects.Meal
import java.time.Instant

//Type converter to serialize and deserialize arraylist to be able to be writable to sqlite database
class ArrayListTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromArrayList(value: ArrayList<String>): String {
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toArrayList(value: String): ArrayList<String> {


            val stringEnums: Collection<String>
            val stringCollection = object : TypeToken<Collection<String>>(){}.type
            stringEnums = Gson().fromJson(value, stringCollection)

            val stringArrayList: ArrayList<String>
            stringArrayList = ArrayList(stringEnums)

            return stringArrayList
        }
    }
}