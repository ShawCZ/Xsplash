package com.shaw.xsplash.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.shaw.xsplash.BR


/**
 * 页面描述：fragment 基类
 *
 * Created by ditclear on 2017/9/27.
 */

abstract class BaseFragment<VB : ViewDataBinding> : Fragment(), Presenter {

    protected val mBinding by lazy {
        DataBindingUtil.inflate<VB>(layoutInflater, getLayoutId(), null, false)
    }

    protected lateinit var mContext: Context
    protected lateinit var mActivity: FragmentActivity

    protected var lazyLoad = false

    protected var visible = false

    /**
     * 标志位，标志已经初始化完成
     */
    protected var isPrepared: Boolean = false
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    protected var hasLoadOnce: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as FragmentActivity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArgs(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = activity ?: throw Exception("activity 为null")
        retainInstance = true
        initView()
        if (lazyLoad) {
            //延迟加载，需重写lazyLoad方法
            lazyLoad()
        } else {
            // 加载数据
            loadData(true)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding.setVariable(BR.presenter, this)
        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    /**
     * 是否可见，延迟加载
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            visible = true
            onVisible()
        } else {
            visible = false
            onInvisible()
        }
    }

    protected fun onInvisible() {

    }

    protected open fun onVisible() {
        lazyLoad()
    }

    override fun loadData(isRefresh: Boolean) {
    }

    override fun onClick(v: View?) {
    }

    open fun lazyLoad() {}

    open fun initArgs(savedInstanceState: Bundle?) {

    }

    abstract fun initView()

    abstract fun getLayoutId(): Int

    protected fun <T> autoWired(key: String, default: T? = null): T? =
        arguments?.let { findWired(it, key, default) }

    private fun <T> findWired(bundle: Bundle, key: String, default: T? = null): T? {
        return if (bundle.get(key) != null) {
            try {
                bundle.get(key) as T
            } catch (e: ClassCastException) {
                e.printStackTrace()
                null
            }
        } else default
    }

    open fun onKeyBackPressed(): Boolean {
        return false
    }

    fun showToast(text: String, context: Context, time: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, text, time).show()
    }
}