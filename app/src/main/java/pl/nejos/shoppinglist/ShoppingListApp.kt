package pl.nejos.shoppinglist

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import pl.nejos.shoppinglist.di.DaggerAppComponent
import javax.inject.Inject


/**
 * Created by SzymonNitecki on 04.02.2018.
 */
class ShoppingListApp : Application(), HasActivityInjector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
    override fun activityInjector() = activityDispatchingAndroidInjector

}