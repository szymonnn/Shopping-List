package pl.nejos.shoppinglist.data.database

import android.arch.persistence.room.*
import pl.nejos.shoppinglist.data.model.ListElement

/**
 * Created by SzymonNitecki on 04.02.2018.
 */
@Dao
interface ListElementDao {
    @Query("SELECT * FROM ListElement WHERE list_id = :listId ORDER BY id DESC")
    fun getAll(listId: Int): MutableList<ListElement>

    @Insert
    fun insert(listElement: ListElement)

    @Delete
    fun remove(listElement: ListElement)

    @Update
    fun update(listElement: ListElement)
}