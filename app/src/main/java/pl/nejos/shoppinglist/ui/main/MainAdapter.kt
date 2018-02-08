package pl.nejos.shoppinglist.ui.main

import pl.nejos.shoppinglist.R
import pl.nejos.shoppinglist.data.model.ShoppingList
import pl.nejos.shoppinglist.ui.common.recycler_view.Adapter

/**
 * Created by SzymonNitecki on 04.02.2018.
 */
class MainAdapter : Adapter<ShoppingList>() {
    override fun getLayoutId(viewType: Int): Int = R.layout.item_shopping_list
}