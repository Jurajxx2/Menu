package com.trasimus.menu.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.trasimus.menu.objects.AddOn
import com.trasimus.menu.objects.Meal

//Database initialise
@Database(entities = [(AddOn::class), (Meal::class)], version = 1)
@TypeConverters(ArrayListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun addOnModel(): AddOnDao

    abstract fun mealModel(): MealDao

    companion object {

        private var sInstance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, "MenuDatabase")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return sInstance!!
        }

        fun destroyInstance() {
            sInstance = null
        }
    }
}
