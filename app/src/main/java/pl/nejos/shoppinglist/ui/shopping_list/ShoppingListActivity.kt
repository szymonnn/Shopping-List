package pl.nejos.shoppinglist.ui.shopping_list

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_shopping_list.*
import kotlinx.android.synthetic.main.view_empty.*
import pl.nejos.shoppinglist.R
import pl.nejos.shoppinglist.data.model.ListElement
import pl.nejos.shoppinglist.databinding.ActivityShoppingListBinding
import pl.nejos.shoppinglist.ui.common.recycler_view.Adapter
import javax.inject.Inject


/**
 * Created by SzymonNitecki on 05.02.2018.
 */
class ShoppingListActivity : AppCompatActivity(), Adapter.OnLongItemClickListener<ListElement>, ShoppingListAdapter.CheckedChangeListener {

    companion object {
        const val EXTRA_SHOPPING_LIST_ID = "extra_shopping_list_id"
        fun start(context: Context, shoppingListId: Int) {
            val intent = Intent(context, ShoppingListActivity::class.java)
            intent.putExtra(EXTRA_SHOPPING_LIST_ID, shoppingListId)
            context.startActivity(intent)
        }
    }

    @Inject lateinit var adapter: ShoppingListAdapter
    @Inject lateinit var viewModel: ShoppingListActivityViewModel
    private lateinit var binding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list)
        binding.viewModel = viewModel
        binding.activity = this
        viewModel.shoppingListId = intent.extras.getInt(EXTRA_SHOPPING_LIST_ID)
        configureView()
        observeShoppingList()
        observeListElements()
    }

    private fun configureView() {
        emptyTitleTextView.setText(R.string.empty_shopping_list_title)
        emptyDescriptionTextView.setText(R.string.empty_shopping_list_description)
        adapter.checkBoxListener = this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        addElementEditText.setOnEditorActionListener({ textView: TextView, i: Int, keyEvent: KeyEvent? ->
            addNewElement(textView)
            return@setOnEditorActionListener true
        })
    }

    override fun onLongItemClick(position: Int, obj: ListElement?) {
        AlertDialog.Builder(this)
                .setItems(R.array.options_list_element, { dialogInterface: DialogInterface, i: Int ->
                    adapter.remove(position)
                    viewModel.removeElement(position)
                }).show()
    }

    private fun observeShoppingList() {
        viewModel.getShoppingList().observe(this, Observer {
            if (it?.archived == true){
                adapter.longClickListener = null
            } else {
                adapter.longClickListener = this
            }
            it?.let{adapter.archived = it.archived}
            binding.archived = it?.archived
            supportActionBar?.title = it?.name
        })
    }

    private fun observeListElements() {
        viewModel.getListElements().observe(this, Observer {
            if (it.orEmpty().isEmpty()){
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.GONE
            }
            it?.let { adapter.updateItems(it.toList()) }
        })
    }

    fun addNewElement(view: View) {
        viewModel.addNewElement()
        addElementEditText.setText("")
    }

    override fun onCheckedChange(isChecked: Boolean, position: Int, obj: ListElement) {
        obj.checked = isChecked
        viewModel.updateListElement(obj)
    }


}