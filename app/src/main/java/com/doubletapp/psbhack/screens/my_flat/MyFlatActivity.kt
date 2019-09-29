package com.doubletapp.psbhack.screens.my_flat

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.flats_arenda.FlatModel
import com.doubletapp.psbhack.screens.service_connection.ConnectionFirstFragment
import kotlinx.android.synthetic.main.my_flats_activity.*
import java.util.ArrayList

class MyFlatActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_flats_activity)
        toolbar.setTitle(R.string.flat)
        toolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        if (savedInstanceState == null) initFragment()

        val flat = intent.getParcelableExtra<FlatModel>(EXTRA_FLAT)
        val fragments = listOf(InfoFragment.newInstance(flat), PaymentsFragment.newInstance(flat))
        val fragmentsAdapter = PagerAdapter(supportFragmentManager, this)
        fragmentsAdapter.setData(fragments)
        fragmentsAdapter.notifyDataSetChanged()
        pager.adapter = fragmentsAdapter
        tabs.setupWithViewPager(pager)
        address.text = (intent.getParcelableExtra(EXTRA_FLAT) as FlatModel).city +
                " " +(intent.getParcelableExtra(EXTRA_FLAT) as FlatModel).address
    }

//    private fun initFragment() {
//        supportFragmentManager.beginTransaction().add(R.id.fragment_container, ConnectionFirstFragment.newInstance()).commit()
//    }

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

    inner class PagerAdapter(fm: FragmentManager, internal var context: Context) :
            FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int) = this.fragments[position]

        override fun getCount() = this.fragments.size

        var fragments: List<Fragment> = ArrayList()
            private set

        fun setData(data: List<Fragment>) {
            this.fragments = data
            notifyDataSetChanged()
        }

        private val tabTitles = arrayOf(getString(R.string.info), getString(R.string.payments))

        override fun getPageTitle(position: Int): CharSequence? {
            return tabTitles[position]
        }
    }

    companion object {
        const val EXTRA_FLAT = "extra_flat"
        @JvmStatic fun getIntent(context: Context, flat: FlatModel): Intent {
            return Intent(context, MyFlatActivity::class.java).apply {
                putExtra(EXTRA_FLAT, flat as Parcelable)
            }
        }
    }
}