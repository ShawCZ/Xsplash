package com.shaw.xsplash.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shaw.xsplash.R
import com.shaw.xsplash.helper.extension.add
import timber.log.Timber


/**
 * Created on 2019/5/22.
 * @author XCZ
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract fun setRootFragment(): BaseFragment<*>
    abstract fun setLayout(): Int
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        initView()
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.frag_container)
        fragment ?: setRootFragment().let {
            Timber.d("add root fragment")
            it.add(this@BaseActivity, it)
        }
    }

    override fun onBackPressed() {
        val fragment =
            supportFragmentManager.findFragmentById(R.id.frag_container) as BaseFragment<*>
        if (!fragment.onKeyBackPressed()) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
        System.runFinalization()
    }
}