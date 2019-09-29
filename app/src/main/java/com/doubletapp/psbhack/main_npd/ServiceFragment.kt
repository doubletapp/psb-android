package com.doubletapp.psbhack.main_npd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.extensions.replaceFragment
import com.doubletapp.psbhack.flats_arenda.FlatsActivity
import kotlinx.android.synthetic.main.fragment_services.*

class ServiceFragment: Fragment() {
    companion object {
        const val ARG_PROFILE = "profile"
        @JvmStatic
        fun newInstance(profile: NpdProfile?) = ServiceFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_PROFILE, profile)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profile = arguments?.getParcelable<NpdProfile>(ARG_PROFILE)

        val repo = ProfileRepo()

        profile?.isArendaConnected?.let {
            if (!it) {
                text_1st.setText(R.string.not_connected)
                rent_oval.setColorFilter(ContextCompat.getColor(context!!, R.color.red))
                btn_1st.setText(R.string.connect)
            }
        }
        profile?.isClientConnected?.let {
            if (!it) {
                text_2st.setText(R.string.not_connected)
                client_oval.setColorFilter(ContextCompat.getColor(context!!, R.color.red))
                btn_2st.setText(R.string.connect)
            }
        }

        btn_1st.setOnClickListener {
            profile?.let {
                if (!it.isArendaConnected) {
                    btn_1st.setText(R.string.go)
                    it.isArendaConnected = true
                    repo.putProfile(it)
                    text_1st.setText(R.string.connected)
                    rent_oval.setColorFilter(ContextCompat.getColor(context!!, R.color.green))
                } else {
                    startActivity(FlatsActivity.getIntent(context!!))
                }

            }
        }

        btn_2st.setOnClickListener {
            profile?.let {
                if (!it.isClientConnected) {
                    btn_2st.setText(R.string.go)
                    it.isClientConnected = true
                    repo.putProfile(it)
                    text_2st.setText(R.string.connected)
                    client_oval.setColorFilter(ContextCompat.getColor(context!!, R.color.green))
                } else {
                    replaceFragment(ComingSoonFragment.newInstance(), android.R.id.content, null)
                }
            }
        }
    }
}