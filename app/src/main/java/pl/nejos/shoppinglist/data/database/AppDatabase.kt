package pl.nejos.shoppinglist.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import pl.nejos.shoppinglist.data.model.ListElement
import pl.nejos.shoppinglist.data.model.ShoppingList

/**
 * Created by SzymonNitecki on 04.02.2018.
 */
@Database(entities = [ShoppingList::class, ListElement::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getShoppingListDao(): ShoppingListDao

    abstract fun getListElementDao(): ListElementDao
}