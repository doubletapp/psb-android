package com.doubletapp.psbhack.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProviders
import com.doubletapp.psbhack.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main), NavigationView.OnNavigationItemSelectedListener {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    companion object {

        private const val ARGS_REQUEST_ID = "args_request_id"

        fun getIntent(context: Context, requestId: String?) = Intent(context, MainActivity::class.java).apply {
            putExtra(ARGS_REQUEST_ID, requestId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(main_toolbar)

        title = "Мой банк"

        val toggle = ActionBarDrawerToggle(this, main_drawer_layout,
                main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        main_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        main_navigation_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            val requestId = intent.getStringExtra(ARGS_REQUEST_ID)
            supportFragmentManager.commit {
                add(R.id.main_content, MainFragment.newInstance(requestId), null)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.main_menu_logout -> {
            main_drawer_layout.closeDrawer(GravityCompat.START)
            viewModel.logout()
            true
        }
        else -> false
    }
}