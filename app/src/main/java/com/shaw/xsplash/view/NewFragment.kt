package com.shaw.xsplash.view

import android.view.View
import com.shaw.xsplash.R
import com.shaw.xsplash.databinding.FragNewBinding
import com.shaw.xsplash.helper.adapter.recycler.ItemClickPresenter
import com.shaw.xsplash.helper.adapter.recycler.SingleTypeAdapter
import com.shaw.xsplash.view.base.BaseFragment
import com.shaw.xsplash.viewmodel.ImageItemViewModel
import com.shaw.xsplash.viewmodel.NewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * Created on 2019/6/5.
 * @author XCZ
 */
class NewFragment : BaseFragment<FragNewBinding>(), ItemClickPresenter<ImageItemViewModel> {

    override fun onItemClick(view: View?, item: ImageItemViewModel) {

    }

    private val mViewModel by viewModel<NewViewModel>()
    private val mAdapter by lazy {
        SingleTypeAdapter<ImageItemViewModel>(mActivity, R.layout.item_new, mViewModel.list)
            .apply {
                itemPresenter = this@NewFragment
            }
    }

    override fun initView() {
        Timber.d("initView")
        mBinding.vm = mViewModel
        mBinding.recycler.adapter = mAdapter
        mViewModel.getNewList()
    }

    override fun getLayoutId(): Int = R.layout.frag_new

    companion object {
        fun newInstance(): NewFragment {
            return NewFragment()
        }
    }
}