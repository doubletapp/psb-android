package com.doubletapp.psbhack.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.doubletapp.psbhack.R

fun Fragment.setupActionBar(@StringRes actionBarTitleStringResource: Int,
                            displayHomeAsUpEnabled: Boolean,
                            @IdRes toolbarID: Int) {

    setupActionBar(getString(actionBarTitleStringResource), displayHomeAsUpEnabled, toolbarID)
}

fun Fragment.setupActionBar(actionBarTitleString: String,
                            displayHomeAsUpEnabled: Boolean,
                            @IdRes toolbarID: Int) {

    val appCompatActivity = activity as AppCompatActivity?

    val toolbar = appCompatActivity?.findViewById<View>(toolbarID) as? Toolbar
    toolbar?.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))

    appCompatActivity?.setSupportActionBar(toolbar)

    val actionBar = appCompatActivity?.supportActionBar
    actionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
    actionBar?.title = actionBarTitleString

    setHasOptionsMenu(displayHomeAsUpEnabled)
}

fun Fragment.addFragment(fragment: Fragment, tag: String?) {
    val transaction = activity?.supportFragmentManager?.beginTransaction()

    transaction?.add(R.id.fragment_container, fragment, tag)
    transaction?.addToBackStack(null)
    transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

    transaction?.commit()
}

fun Fragment.replaceFragment(fragment: Fragment, container: Int , tag: String?) {
    val transaction = activity?.supportFragmentManager?.beginTransaction()

    transaction?.replace(container, fragment, tag)
    transaction?.addToBackStack(null)
    transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

    transaction?.commit()
}

fun Fragment.replaceFragment(fragment: Fragment, tag: String?) {
    val transaction = activity?.supportFragmentManager?.beginTransaction()

    transaction?.replace(R.id.fragment_container, fragment, tag)
    transaction?.addToBackStack(null)
    transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

    transaction?.commit()
}