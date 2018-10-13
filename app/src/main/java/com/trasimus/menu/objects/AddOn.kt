package com.trasimus.menu.objects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "AddOns")
class AddOn {

    @PrimaryKey()
    var id: String = ""

    @ColumnInfo(name = "price")
    var price: Float = 0.toFloat()

    @ColumnInfo(name = "size")
    var size: String = ""

    @ColumnInfo(name = "type")
    var type: String = ""

    @ColumnInfo(name = "name")
    var name: String = ""


    constructor()

    @Ignore
    constructor(id: String, name: String, type: String, price: Float, size: String) {
        this.id = id
        this.name = name
        this.type = type
        this.price = price
        this.size = size
    }
}
