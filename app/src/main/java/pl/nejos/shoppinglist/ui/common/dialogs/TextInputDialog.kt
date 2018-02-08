package pl.nejos.shoppinglist.ui.common.dialogs

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.dialog_text_input.*
import pl.nejos.shoppinglist.R

/**
 * Created by SzymonNitecki on 04.02.2018.
 */
class TextInputDialog(context: Context): AlertDialog(context){
    var listener = {string: String -> }
    init {
        setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.add), { dialogInterface: DialogInterface, i: Int ->
            if (textInput.text.toString().isNullOrBlank()) {
                listener.invoke("-")
            } else {
                listener.invoke(textInput.text.toString())
            }
        })
        setView(LayoutInflater.from(context).inflate(R.layout.dialog_text_input, null))
    }
}