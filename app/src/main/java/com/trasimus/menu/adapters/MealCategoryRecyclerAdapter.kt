package com.trasimus.menu.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.trasimus.menu.R
import com.trasimus.menu.objects.Meal
import com.hendraanggrian.widget.ExpandableItem
import com.hendraanggrian.widget.ExpandableRecyclerView


class MealCategoryRecyclerAdapter(private val mealList: List<Meal>, private val context: Context, private val layoutManager: LinearLayoutManager) : ExpandableRecyclerView.Adapter<MealCategoryRecyclerAdapter.MyViewHolder>(layoutManager) {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val item: ExpandableItem = view.findViewById(R.id.mealRow) as ExpandableItem
        var title: TextView = item.headerLayout.findViewById(R.id.mealCategory)
        var meals: RecyclerView = item.contentLayout.findViewById(R.id.mealsList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.meal_category_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var categoryMeals: ArrayList<Meal> = arrayListOf()

        var meal = Meal()
        var category = ""
        var order = 0
        mealList.forEach {
            if (it.category != category){
                category = it.category
                if (order == position){
                    meal = it
                }
                order++
            }

            if (meal.category == it.category){
                categoryMeals.add(it)
            }
        }

        holder.title.text = meal.category

        holder.meals.layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT

        val mAdapter = MealRecyclerAdapter(categoryMeals, context)
        val mLayoutManager = LinearLayoutManager(context)
        holder.meals.layoutManager = mLayoutManager
        holder.meals.itemAnimator = DefaultItemAnimator()
        holder.meals.adapter = mAdapter
    }

    override fun getItemCount(): Int {
        var category = ""
        var size = 0
        mealList.forEach {
            if (it.category != category){
                category = it.category
                size++
            }
        }
        return size
    }
}
