package com.shaw.xsplash.helper.adapter.recycler

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import timber.log.Timber


/**
 * 页面描述：SingleTypeAdapter
 */
open class SingleTypeAdapter<T>(context: Context, private val layoutRes: Int, list: ObservableList<T>) :
    BaseViewAdapter<T>(context, list) {

    init {
        list.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<T>>() {
            override fun onChanged(contributorViewModels: ObservableList<T>) {
                Timber.d("SingleTypeAdapter --onChanged")
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(contributorViewModels: ObservableList<T>, i: Int, i1: Int) {
                Timber.d("SingleTypeAdapter --onItemRangeChanged")
                notifyItemRangeChanged(i, i1)
            }

            override fun onItemRangeInserted(contributorViewModels: ObservableList<T>, i: Int, i1: Int) {
                Timber.d("SingleTypeAdapter --onItemRangeInserted")
                notifyItemRangeInserted(i, i1)
            }

            override fun onItemRangeMoved(contributorViewModels: ObservableList<T>, i: Int, i1: Int, i2: Int) {
                Timber.d("SingleTypeAdapter --onItemRangeMoved")
                notifyItemMoved(i, i1)
            }

            override fun onItemRangeRemoved(contributorViewModels: ObservableList<T>, i: Int, i1: Int) {
                Timber.d("SingleTypeAdapter --onItemRangeRemoved")
                if (contributorViewModels.isEmpty()) {
//                    mLastPosition = -1
                    notifyDataSetChanged()
                } else {
                    notifyItemRangeRemoved(i, i1)
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BindingViewHolder(DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutRes, parent, false))
}