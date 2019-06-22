package com.shaw.xsplash.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.shaw.xsplash.R
import com.shaw.xsplash.model.repo.NewRepository
import timber.log.Timber

/**
 * Created on 2019/6/5.
 * @author XCZ
 */
class NewViewModel(private val repository: NewRepository) : ViewModel() {
    val list = ObservableArrayList<ImageItemViewModel>()

    fun getNewList() {
        Timber.d("load data")
        list.add(ImageItemViewModel(R.drawable.ic_launcher_background,200))
        list.add(ImageItemViewModel(R.drawable.ic_launcher_background,300))
        list.add(ImageItemViewModel(R.drawable.ic_launcher_background,100))
        list.add(ImageItemViewModel(R.drawable.ic_launcher_background,400))
        list.add(ImageItemViewModel(R.drawable.ic_launcher_background,150))
    }
}