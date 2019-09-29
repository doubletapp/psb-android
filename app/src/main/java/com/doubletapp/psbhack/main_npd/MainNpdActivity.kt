package com.doubletapp.psbhack.main_npd

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainNpdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(android.R.id.content, MainNpdFragment.newInstance(), null)
            }
        }
    }

    companion object {
        @JvmStatic fun getIntent(context: Context, isClearTop: Boolean = false): Intent {
            return Intent(context, MainNpdActivity::class.java).apply {
                if (isClearTop) flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
        }
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