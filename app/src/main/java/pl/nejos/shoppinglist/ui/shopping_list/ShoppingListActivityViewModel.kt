package pl.nejos.shoppinglist.ui.shopping_list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import pl.nejos.shoppinglist.data.database.AppDatabase
import pl.nejos.shoppinglist.data.model.ListElement
import pl.nejos.shoppinglist.data.model.ShoppingList

/**
 * Created by SzymonNitecki on 05.02.2018.
 */
class ShoppingListActivityViewModel(private val database: AppDatabase): ViewModel(){
    val shoppingList = MutableLiveData<ShoppingList>()
    val listElements = MutableLiveData<MutableList<ListElement>>()
    val newElement = ObservableField<String>()
    var shoppingListId: Int = -1

    fun getShoppingList(): LiveData<ShoppingList>{
        shoppingList.value = database.getShoppingListDao().get(shoppingListId)
        return shoppingList
    }

    fun getListElements(): LiveData<MutableList<ListElement>>{
        listElements.value = database.getListElementDao().getAll(shoppingListId)
        return listElements
    }

    fun addNewElement(){
        if (!newElement.get().isNullOrBlank()) {
            database.getListElementDao().insert(ListElement(name = newElement.get(), listId = shoppingListId))
            getListElements()
        }
    }

    fun removeElement(position: Int) {
        listElements.value?.get(position)?.let { database.getListElementDao().remove(it) }
        listElements.value?.removeAt(position)
    }

    fun updateListElement(listElement: ListElement) {
        database.getListElementDao().update(listElement)
    }


}