package pl.nejos.shoppinglist.ui.shopping_list

import android.widget.CheckBox
import pl.nejos.shoppinglist.R
import pl.nejos.shoppinglist.data.model.ListElement
import pl.nejos.shoppinglist.ui.common.recycler_view.Adapter

/**
 * Created by SzymonNitecki on 05.02.2018.
 */
class ShoppingListAdapter: Adapter<ListElement>(){
    var checkBoxListener: CheckedChangeListener? = null
    var archived: Boolean = false

    override fun getLayoutId(viewType: Int) = R.layout.item_list_element

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        super.onBindViewHolder(holder, position)
        val checkbox = holder?.binding?.root?.findViewById<CheckBox>(R.id.checkbox)
        checkbox?.isEnabled = !archived
        checkbox?.isChecked = getItem(position).checked
        checkbox?.setOnCheckedChangeListener { buttonView, isChecked ->
            checkBoxListener?.onCheckedChange(isChecked, position, getItem(position))
        }
    }

    interface CheckedChangeListener{
        fun onCheckedChange(isChecked: Boolean, position: Int, obj: ListElement)
    }

}