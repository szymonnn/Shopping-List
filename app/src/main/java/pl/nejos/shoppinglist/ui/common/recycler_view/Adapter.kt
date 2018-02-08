package pl.nejos.shoppinglist.ui.common.recycler_view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR

/**
 * Created by SzymonNitecki on 05.02.2018.
 */
abstract class Adapter<T> : RecyclerView.Adapter<Adapter<T>.ViewHolder>(){
    private val objectsList = mutableListOf<T>()
    var clickListener: OnItemClickListener<T>? = null
    var longClickListener: OnLongItemClickListener<T>? = null

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(objectsList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent?.context), getLayoutId(viewType), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = objectsList.count()

    fun updateItems(list: List<T>) {
        if (list.size < objectsList.size){
            objectsList.clear()
            objectsList.addAll(list)
            notifyDataSetChanged()
        } else {
            val result = DiffUtil.calculateDiff(DiffUtilCallback(list, list))
            objectsList.clear()
            objectsList.addAll(list)
            result.dispatchUpdatesTo(this)
        }
    }

    fun getItem(position: Int) = objectsList[position]

    fun remove(position: Int) {
        objectsList.removeAt(position)
        notifyItemRemoved(position)
    }

    abstract fun getLayoutId(viewType: Int): Int

    inner class ViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(obj: T) {
            binding.root.setOnClickListener {
                clickListener?.onItemClick(adapterPosition, obj)
            }
            binding.root.setOnLongClickListener {
                longClickListener?.onLongItemClick(adapterPosition, obj)
                return@setOnLongClickListener true
            }
            binding.setVariable(BR.obj, obj)
            binding.executePendingBindings()
        }

    }

    interface OnItemClickListener<in T>{
        fun onItemClick(position: Int, obj: T?)
    }

    interface OnLongItemClickListener<in T>{
        fun onLongItemClick(position: Int, obj: T?)
    }
}