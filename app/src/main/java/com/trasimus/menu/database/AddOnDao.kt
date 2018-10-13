package com.trasimus.menu.database

import android.arch.persistence.room.*
import com.trasimus.menu.objects.AddOn

@Dao
interface AddOnDao {
    @get:Query("SELECT * FROM AddOns")
    val allAddOns: List<AddOn>

    @Query("SELECT * FROM AddOns where id = :id")
    fun getAddOnByID(id: String): AddOn

    @Query("SELECT * FROM AddOns WHERE   id = (SELECT MAX(id)  FROM AddOns)")
    fun getLastAddOn() : AddOn

    @Query("DELETE FROM AddOns")
    fun deleteAllAddOns()

    @Insert
    fun insertAddOn(AddOn: AddOn)

    @Delete
    fun deleteAddOn(AddOn: AddOn)

    @Update
    fun updateAddOn(AddOn: AddOn)
}
