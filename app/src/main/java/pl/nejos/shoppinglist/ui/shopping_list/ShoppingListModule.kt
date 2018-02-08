package pl.nejos.shoppinglist.ui.shopping_list

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import pl.nejos.shoppinglist.data.database.AppDatabase
import pl.nejos.shoppinglist.utils.ViewModelFactory

/**
 * Created by SzymonNitecki on 05.02.2018.
 */
@Module
class ShoppingListModule {
    @Provides
    fun provideViewModel(shoppingListActivity: ShoppingListActivity, database: AppDatabase) = ViewModelProviders.of(shoppingListActivity, ViewModelFactory(database)).get(ShoppingListActivityViewModel::class.java)

    @Provides
    fun provideShoppingListAdapter() = ShoppingListAdapter()
}