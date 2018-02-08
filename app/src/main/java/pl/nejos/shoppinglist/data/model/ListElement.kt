package pl.nejos.shoppinglist.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import pl.nejos.shoppinglist.ui.common.recycler_view.DiffCheck

/**
 * Created by SzymonNitecki on 04.02.2018.
 */
@Entity(foreignKeys = [ForeignKey(entity = ShoppingList::class, parentColumns = arrayOf("id"), childColumns = arrayOf("list_id"))])
data class ListElement(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        var name: String = "",
        var checked: Boolean = false,
        @ColumnInfo(name = "list_id") var listId: Int? = -1) : DiffCheck<ListElement> {
    override fun areContentsTheSame(obj: ListElement) = areItemsTheSame(obj)

    override fun areItemsTheSame(obj: ListElement) = id == obj.id

}
