package com.shaw.xsplash.helper.adapter.recycler

import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.shaw.xsplash.BR

/**
 * Created on 2019/5/22.
 * @author XCZ
 */
abstract class BaseViewAdapter<T>(context: Context, private val list: ObservableList<T>) :
    RecyclerView.Adapter<BindingViewHolder<ViewDataBinding>>() {

    protected val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    var itemPresenter: ItemClickPresenter<T>? = null

    override fun onBindViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        val item = list[position]
        holder.binding.setVariable(BR.item, item)
        holder.binding.setVariable(BR.presenter, itemPresenter)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = list.size

    fun getItem(position: Int): T? = list[position]
}

