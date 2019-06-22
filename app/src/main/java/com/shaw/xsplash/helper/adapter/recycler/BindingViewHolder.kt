package com.shaw.xsplash.helper.adapter.recycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 2019/5/22.
 * @author XCZ
 */
open class BindingViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)