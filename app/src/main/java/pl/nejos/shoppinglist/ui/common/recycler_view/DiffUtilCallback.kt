package pl.nejos.shoppinglist.ui.common.recycler_view

import android.support.v7.util.DiffUtil

/**
 * Created by SzymonNitecki on 05.02.2018.
 */
class DiffUtilCallback<T>(private val newList: List<T>, private val oldList: List<T>): DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean{
        try{
            val newObj = newList[newItemPosition] as DiffCheck<Any>
            val oldObj = oldList[oldItemPosition] as DiffCheck<Any>
            return newObj.areItemsTheSame(oldObj)
        } catch (exception: ClassCastException){
            throw ClassCastException("Object must implement DiffChecker")
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = false

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

}