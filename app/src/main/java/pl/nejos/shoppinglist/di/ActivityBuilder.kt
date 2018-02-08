package pl.nejos.shoppinglist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.nejos.shoppinglist.ui.main.MainActivity
import pl.nejos.shoppinglist.ui.main.MainActivityModule
import pl.nejos.shoppinglist.ui.shopping_list.ShoppingListActivity
import pl.nejos.shoppinglist.ui.shopping_list.ShoppingListModule


/**
 * Created by SzymonNitecki on 04.02.2018.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ShoppingListModule::class])
    abstract fun bindShoppingListActivity(): ShoppingListActivity
}
