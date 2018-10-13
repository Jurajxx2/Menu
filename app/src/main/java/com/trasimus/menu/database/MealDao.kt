package com.trasimus.menu.database

import android.arch.persistence.room.*
import com.trasimus.menu.objects.Meal

@Dao
interface MealDao {
    @get:Query("SELECT * FROM Meals")
    val allMeals: List<Meal>

    @Query("SELECT * FROM Meals where id = :id")
    fun getMealByID(id: String): Meal

    @Query("SELECT * FROM Meals WHERE   id = (SELECT MAX(id)  FROM Meals)")
    fun getLastMeal() : Meal

    @Query("DELETE FROM Meals")
    fun deleteAllMeals()

    @Insert
    fun insertMeal(Meal: Meal)

    @Delete
    fun deleteMeal(Meal: Meal)

    @Update
    fun updateMeal(Meal: Meal)
}
