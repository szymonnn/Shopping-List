package pl.nejos.shoppinglist.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.nejos.shoppinglist.data.database.AppDatabase
import javax.inject.Singleton




/**
 * Created by SzymonNitecki on 04.02.2018.
 */
@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "shopping_list_db").fallbackToDestructiveMigration().allowMainThreadQueries().build()

}