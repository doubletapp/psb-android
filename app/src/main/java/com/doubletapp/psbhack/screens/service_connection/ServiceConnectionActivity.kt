package com.doubletapp.psbhack.screens.service_connection

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.doubletapp.psbhack.R

class ServiceConnectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragments_activity)
        if (savedInstanceState == null) initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, ConnectionFirstFragment.newInstance()).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {

            android.R.id.home -> {

                handleBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handleBackPressed() {
//        val fragment = (supportFragmentManager.findFragmentById(R.id.fragment_container) as? BackPressHandler)

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else /*if (fragment?.handleBackPress() != true)*/ {
            super.onBackPressed()
        }
    }

    override fun onBackPressed() = handleBackPressed()
}