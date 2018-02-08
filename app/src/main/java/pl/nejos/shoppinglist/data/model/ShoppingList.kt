package pl.nejos.shoppinglist.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import pl.nejos.shoppinglist.ui.common.recycler_view.DiffCheck
import java.util.*

/**
 * Created by SzymonNitecki on 04.02.2018.
 */
@Entity
data class ShoppingList(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        var name: String = "",
        var archived: Boolean = false,
        var createdAt: Date = Date()): DiffCheck<ShoppingList>{
    override fun areContentsTheSame(obj: ShoppingList) = areItemsTheSame(obj)

    override fun areItemsTheSame(obj: ShoppingList) = id == obj.id

}