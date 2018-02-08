package pl.nejos.shoppinglist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import pl.nejos.shoppinglist.ShoppingListApp
import javax.inject.Singleton

/**
 * Created by SzymonNitecki on 04.02.2018.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: ShoppingListApp)
}