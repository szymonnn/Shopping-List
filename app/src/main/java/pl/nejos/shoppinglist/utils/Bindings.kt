package pl.nejos.shoppinglist.utils

import android.databinding.BindingAdapter
import android.widget.TextView
import java.text.DateFormat
import java.util.*

/**
 * Created by SzymonNitecki on 05.02.2018.
 */
object Bindings{
    @JvmStatic
    @BindingAdapter("date")
    fun setDate(textView: TextView, date: Date){
        textView.text = DateFormat.getDateInstance().format(date)
    }
}