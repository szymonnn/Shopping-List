package pl.nejos.shoppinglist.ui.main

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import pl.nejos.shoppinglist.data.database.AppDatabase
import pl.nejos.shoppinglist.utils.ViewModelFactory


/**
 * Created by SzymonNitecki on 04.02.2018.
 */
@Module
class MainActivityModule {
    @Provides
    fun provideViewModel(mainActivity: MainActivity, database: AppDatabase) = ViewModelProviders.of(mainActivity, ViewModelFactory(database)).get(MainActivityViewModel::class.java)

    @Provides
    fun provideMainAdapter() = MainAdapter()
}