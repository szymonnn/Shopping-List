package pl.nejos.shoppinglist.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pl.nejos.shoppinglist.data.database.AppDatabase
import pl.nejos.shoppinglist.data.model.ShoppingList

/**
 * Created by SzymonNitecki on 04.02.2018.
 */
class MainActivityViewModel(private val database: AppDatabase) : ViewModel(){
    var archived: Boolean = false
    val shoppingLists = MutableLiveData<MutableList<ShoppingList>>()

    fun toggleArchive(){
        archived = !archived
        getShoppingLists()
    }

    fun getShoppingLists(): LiveData<MutableList<ShoppingList>>{
        shoppingLists.value = database.getShoppingListDao().getAll(archived)
        return shoppingLists
    }

    fun createNewList(name: String){
        database.getShoppingListDao().insert(ShoppingList(name = name))
        shoppingLists.value = database.getShoppingListDao().getAll(archived)
    }

    fun updateShoppingList(shoppingList: ShoppingList?) {
        database.getShoppingListDao().update(shoppingList)
        shoppingLists.value = database.getShoppingListDao().getAll(archived)
    }
}