package pl.nejos.shoppinglist.ui.common.recycler_view

/**
 * Created by SzymonNitecki on 05.02.2018.
 */
interface DiffCheck<T>{
    fun areContentsTheSame(obj: T): Boolean

    fun areItemsTheSame(obj: T): Boolean
}