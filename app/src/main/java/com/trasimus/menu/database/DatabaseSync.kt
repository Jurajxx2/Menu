package com.trasimus.menu.database

import android.app.Activity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trasimus.menu.objects.AddOn
import com.trasimus.menu.objects.Meal
import java.net.URL

class DatabaseSync(activity: Activity) {

    var meals: List<Meal>
    private var mealsDatabase: List<Meal>
    private var mealEnums: Collection<Meal>
    private var mealJson: String

    var addOns: List<AddOn>
    private var addOnDatabase: List<AddOn>
    private var addOnEnums: Collection<AddOn>
    private var addOnJson: String

    private var database: AppDatabase = AppDatabase.getInstance(activity.applicationContext)

    init {
        mealsDatabase = database.mealModel().allMeals

        mealJson = URL("https://api.dev.breweria.garwan.io/api/restaurant/slimak/meals").readText()
        val mealCollection = object : TypeToken<Collection<Meal>>(){}.type
        mealEnums = Gson().fromJson(mealJson, mealCollection)

        meals = mealEnums.toList()


        addOnDatabase = database.addOnModel().allAddOns

        addOnJson = URL("https://api.dev.breweria.garwan.io/api/restaurant/slimak/addOns").readText()
        val addOnCollection = object : TypeToken<Collection<AddOn>>(){}.type
        addOnEnums = Gson().fromJson(addOnJson, addOnCollection)

        addOns = addOnEnums.toList()
    }

    fun clearDatabase(){
        database.clearAllTables()
    }

    fun sync(){

    }

}