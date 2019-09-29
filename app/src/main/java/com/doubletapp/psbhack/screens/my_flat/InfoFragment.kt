package com.doubletapp.psbhack.screens.my_flat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.flats_arenda.FlatModel
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {
    companion object {
        const val ARG_FLAT = "flat"
        @JvmStatic fun newInstance(flat: FlatModel?) = InfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_FLAT, flat)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val flat = arguments?.getParcelable<FlatModel>(ARG_FLAT)
        name.text = flat?.arendatorName
        passp.text = flat?.arendatorPassport
        birth_date.text = flat?.arandatorDate
        cost.text = flat?.cost
        state.text = flat?.status
        leave_date.text = flat?.date
    }
}