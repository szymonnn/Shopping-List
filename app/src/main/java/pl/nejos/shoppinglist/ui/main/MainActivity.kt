package pl.nejos.shoppinglist.ui.main

import android.arch.lifecycle.Observer
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_empty.*
import pl.nejos.shoppinglist.R
import pl.nejos.shoppinglist.data.model.ShoppingList
import pl.nejos.shoppinglist.databinding.ActivityMainBinding
import pl.nejos.shoppinglist.ui.common.dialogs.TextInputDialog
import pl.nejos.shoppinglist.ui.common.recycler_view.Adapter
import pl.nejos.shoppinglist.ui.shopping_list.ShoppingListActivity
import javax.inject.Inject


class MainActivity : AppCompatActivity(), Adapter.OnItemClickListener<ShoppingList>, Adapter.OnLongItemClickListener<ShoppingList> {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    @Inject
    lateinit var listsAdapter: MainAdapter

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        binding.viewModel = viewModel
        configureRecyclerView()
        configureNavigation()
        observeShoppingLists()
    }

    override fun onBackPressed() {
        if (bottomNavigationBar.selectedItemId == R.id.action_archived){
            bottomNavigationBar.menu.findItem(R.id.action_current).isChecked = true
        } else {
            super.onBackPressed()
        }
    }

    private fun configureRecyclerView() {
        listsAdapter.clickListener = this
        listsAdapter.longClickListener = this
        recyclerView.adapter = listsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun configureNavigation() {
        bottomNavigationBar.setOnNavigationItemSelectedListener {
            val id = it.itemId
            listsAdapter.updateItems(listOf())
            viewModel.toggleArchive()
            if (id == R.id.action_archived){
                floatingActionButton.visibility = View.INVISIBLE
                emptyDescriptionTextView.setText(R.string.empty_archive_description)
                emptyTitleTextView.setText(R.string.empty_archive_title)
            } else if (id == R.id.action_current){
                floatingActionButton.visibility = View.VISIBLE
                emptyDescriptionTextView.setText(R.string.empty_current_description)
                emptyTitleTextView.setText(R.string.empty_current_title)
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun observeShoppingLists() {
        viewModel.getShoppingLists().observe(this, Observer {
            if (it.orEmpty().isEmpty()){
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.GONE
            }
            listsAdapter.updateItems(it.orEmpty())
        })
    }

    fun createNewList(view: View) {
        val dialog = TextInputDialog(this)
        dialog.listener = { text: String ->
            viewModel.createNewList(text)
        }
        dialog.setTitle(R.string.enter_list_name)
        dialog.show()
    }

    override fun onItemClick(position: Int, obj: ShoppingList?) {
        if (obj != null)
            ShoppingListActivity.start(this, obj.id)
    }

    override fun onLongItemClick(position: Int, obj: ShoppingList?) {
        val items = if (viewModel.archived) R.array.options_shopping_list_archived else R.array.options_shopping_list
        AlertDialog.Builder(this)
                .setItems(items, { dialogInterface: DialogInterface, i: Int ->
                    obj?.let{obj.archived = !obj.archived}
                    listsAdapter.remove(position)
                    viewModel.updateShoppingList(obj)
                }).show()
    }

}
