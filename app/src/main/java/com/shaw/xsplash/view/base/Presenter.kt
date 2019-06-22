package com.shaw.xsplash.view.base

import android.view.View

interface Presenter : View.OnClickListener {

    fun loadData(isRefresh: Boolean)
    override fun onClick(v: View?)
}