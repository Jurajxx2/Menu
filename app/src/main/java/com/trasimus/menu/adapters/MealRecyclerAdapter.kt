package com.trasimus.menu.adapters

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.trasimus.menu.AddOns
import com.trasimus.menu.R
import com.trasimus.menu.objects.Meal
import org.jetbrains.anko.sdk27.coroutines.onClick

class MealRecyclerAdapter(private val mealList: List<Meal>, private val context: Context) : RecyclerView.Adapter<MealRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.meal)
        var price: TextView = view.findViewById(R.id.price)
        var size: TextView = view.findViewById(R.id.size)
        var showAddOns: Button = view.findViewById(R.id.showAddOns)
        var background: ConstraintLayout = view.findViewById(R.id.backgroundMeal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.meal_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = mealList[position].name
        holder.price.text = mealList[position].price.toString()
        holder.size.text = mealList[position].size

        if (mealList[position].addons.size>0){
            holder.showAddOns.onClick {
                val intent = Intent(context, AddOns::class.java)
                intent.putExtra("addons", mealList[position].addons)
                context.startActivity(intent)
            }
            holder.background.onClick {
                val intent = Intent(context, AddOns::class.java)
                intent.putExtra("addons", mealList[position].addons)
                context.startActivity(intent)
            }
        } else {
            holder.showAddOns.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }
}