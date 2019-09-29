package com.doubletapp.psbhack.flats_arenda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.doubletapp.psbhack.R

class FlatsActivity : AppCompatActivity(R.layout.activity_empty) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(android.R.id.content, FlatsArendaFragment(), null)
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

    companion object {
        @JvmStatic fun getIntent(context: Context): Intent {
            return Intent(context, FlatsActivity::class.java).apply {
            }
        }
    }

    override fun onBackPressed() = handleBackPressed()
}