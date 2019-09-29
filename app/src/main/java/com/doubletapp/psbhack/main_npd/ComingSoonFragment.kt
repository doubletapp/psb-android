package com.doubletapp.psbhack.main_npd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.extensions.setupActionBar

class ComingSoonFragment  : Fragment() {
    companion object {
        @JvmStatic fun newInstance() = ComingSoonFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coming_soon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar(R.string.service_connect, true, R.id.toolbar)
    }
}