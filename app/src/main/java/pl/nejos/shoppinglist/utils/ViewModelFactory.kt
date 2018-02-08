package pl.nejos.shoppinglist.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import pl.nejos.shoppinglist.data.database.AppDatabase

/**
 * Created by SzymonNitecki on 04.02.2018.
 */
class ViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AppDatabase::class.java).newInstance(database)
    }
}