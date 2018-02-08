package pl.nejos.shoppinglist.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import pl.nejos.shoppinglist.data.model.ShoppingList

/**
 * Created by SzymonNitecki on 04.02.2018.
 */
@Dao
interface ShoppingListDao{
    @Query("SELECT * FROM ShoppingList WHERE archived = :archived ORDER BY createdAt DESC")
    fun getAll(archived: Boolean): MutableList<ShoppingList>

    @Insert
    fun insert(shoppingList: ShoppingList)

    @Query("SELECT * FROM ShoppingList WHERE id = :id")
    fun get(id: Int): ShoppingList

    @Update
    fun update(shoppingList: ShoppingList?)
}