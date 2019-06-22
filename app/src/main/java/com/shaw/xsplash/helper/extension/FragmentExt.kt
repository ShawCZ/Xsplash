package com.shaw.xsplash.helper.extension

/**
 * Various extension functions for AppCompatActivity.
 */

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.shaw.xsplash.R

/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 */
fun Fragment.replace(activity: FragmentActivity, fragment: Fragment) {
    activity.supportFragmentManager.transact {
        replace(R.id.frag_container, fragment)
    }
}

/**
 * The `fragment` is added to the container view with tag. The operation is
 * performed by the `fragmentManager`.
 */
fun Fragment.addToBackStack(activity: FragmentActivity, fragment: Fragment) {
    activity.supportFragmentManager.transact {
        hide(this@addToBackStack)
        addToBackStack(null)
        add(R.id.frag_container,fragment)
        show(fragment)
    }
}
fun Fragment.add(activity: FragmentActivity, fragment: Fragment ) {
    activity.supportFragmentManager.transact {
        add(R.id.frag_container,fragment)
        show(fragment)
    }
}

fun Fragment.pop(activity: FragmentActivity) {
    activity.supportFragmentManager.popBackStack()
}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}