package com.shaw.xsplash.helper.adapter.recycler

import android.view.View

/**
 * Created on 2019/5/22.
 * @author XCZ
 */
interface ItemClickPresenter<in Any> {
    fun onItemClick(view: View? = null, item: Any)
    fun onItemChildClick(view: View? = null, item: Any){}
}