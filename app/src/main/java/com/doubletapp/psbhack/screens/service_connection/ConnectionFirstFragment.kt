package com.doubletapp.psbhack.screens.service_connection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.extensions.replaceFragment
import com.doubletapp.psbhack.extensions.setupActionBar
import kotlinx.android.synthetic.main.fragment_connection_first.*

class ConnectionFirstFragment : Fragment() {
    companion object {
        @JvmStatic fun newInstance() = ConnectionFirstFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_connection_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar(R.string.service_connect, true, R.id.toolbar)
        next_btn.setOnClickListener {
            replaceFragment(ConnectionSecondFragment.newInstance(), null)
        }
    }
}