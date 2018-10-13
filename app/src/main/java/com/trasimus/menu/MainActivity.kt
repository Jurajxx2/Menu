package com.trasimus.menu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.trasimus.menu.objects.Meal
import java.net.URL
import com.google.gson.reflect.TypeToken
import com.trasimus.menu.adapters.MealCategoryRecyclerAdapter
import com.trasimus.menu.database.AppDatabase
import com.trasimus.menu.objects.AddOn
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.lang.IllegalStateException
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {

    private lateinit var meals: List<Meal>
    private lateinit var mealsDatabase: List<Meal>
    private lateinit var mealEnums: Collection<Meal>
    private lateinit var mealJson: String

    private lateinit var addOns: List<AddOn>
    private lateinit var addOnDatabase: List<AddOn>
    private lateinit var addOnEnums: Collection<AddOn>
    private lateinit var addOnJson: String

    private lateinit var database: AppDatabase

    private lateinit var recyclerView: com.hendraanggrian.widget.ExpandableRecyclerView
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: MealCategoryRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getInstance(this)

        doAsync {

            syncWithAPI()

            addOnDatabase = database.addOnModel().allAddOns

            mealsDatabase = database.mealModel().allMeals

            uiThread {
                recyclerView = findViewById(R.id.mealCateogryList)
                mLayoutManager = LinearLayoutManager(applicationContext)
                mAdapter = MealCategoryRecyclerAdapter(mealsDatabase, this@MainActivity, mLayoutManager)
                recyclerView.layoutManager = mLayoutManager
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.adapter = mAdapter

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.showAddOns -> {
            doAsync {
                syncWithAPI()
                uiThread {
                    mAdapter = MealCategoryRecyclerAdapter(mealsDatabase, this@MainActivity, mLayoutManager)
                    mAdapter.notifyDataSetChanged()
                }
            }
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun syncWithAPI(){
            mealJson = URL("https://api.dev.breweria.garwan.io/api/restaurant/slimak/meals").readText()
            val mealCollection = object : TypeToken<Collection<Meal>>() {}.type
            mealEnums = Gson().fromJson(mealJson, mealCollection)

            meals = mealEnums.toList()

            meals = meals.sortedWith(compareBy { it.category })


            addOnJson = URL("https://api.dev.breweria.garwan.io/api/restaurant/slimak/addons").readText()
            val addOnCollection = object : TypeToken<Collection<AddOn>>() {}.type
            addOnEnums = Gson().fromJson(addOnJson, addOnCollection)

            addOns = addOnEnums.toList()

            addOns = addOns.sortedWith(compareBy { it.type })

            if (meals.isNotEmpty()) {
                database.mealModel().deleteAllMeals()
                meals.forEach {
                    database.mealModel().insertMeal(it)
                }
            }

            if (addOns.isNotEmpty()) {
                database.addOnModel().deleteAllAddOns()
                addOns.forEach {
                    database.addOnModel().insertAddOn(it)
                }
            }

    }
}
