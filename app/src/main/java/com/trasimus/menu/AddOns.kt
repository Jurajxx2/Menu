package com.trasimus.menu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trasimus.menu.adapters.AddOnCategoryRecycleAdapter
import com.trasimus.menu.adapters.MealCategoryRecyclerAdapter
import com.trasimus.menu.database.AppDatabase
import com.trasimus.menu.objects.AddOn
import com.trasimus.menu.objects.Meal
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class AddOns : AppCompatActivity() {

    private lateinit var addOns: ArrayList<AddOn>
    private lateinit var addOnDatabase: List<AddOn>
    private lateinit var addOnEnums: Collection<AddOn>
    private lateinit var addOnJson: String
    private lateinit var extras: Bundle
    private lateinit var mealAddOns: ArrayList<String>
    private lateinit var allAddOns: Collection<AddOn>

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ons)

        extras = intent.extras
        mealAddOns = extras.getStringArrayList("addons")
        addOns = arrayListOf()

        database = AppDatabase.getInstance(this)

        doAsync {

            mealAddOns.forEach {
                addOns.add(database.addOnModel().getAddOnByID(it))
            }

            uiThread {
                var recyclerView = findViewById<com.hendraanggrian.widget.ExpandableRecyclerView>(R.id.addOnCategoryList)
                val mLayoutManager = LinearLayoutManager(applicationContext)
                var mAdapter = AddOnCategoryRecycleAdapter(addOns, this@AddOns, mLayoutManager)
                recyclerView!!.layoutManager = mLayoutManager
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.adapter = mAdapter

            }
        }
    }
}
