package com.trasimus.menu.objects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Meals")
class Meal {

    @PrimaryKey()
    var id: String = ""

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "category")
    var category: String = ""

    @ColumnInfo(name = "price")
    var price: Float = 0.toFloat()

    @ColumnInfo(name = "size")
    var size: String = ""

    @ColumnInfo(name = "image")
    var image: String = ""

    @ColumnInfo(name = "addons")
    var addons: ArrayList<String> = arrayListOf()

    @ColumnInfo(name = "description")
    var description: String = ""


    constructor()

    @Ignore
    constructor(id: String, name: String, category: String, price: Float, size: String, image: String, addons: ArrayList<String>, description: String) {
        this.id = id
        this.name = name
        this.category = category
        this.price = price
        this.size = size
        this.image = image
        this.addons = addons
        this.description = description
    }
}
