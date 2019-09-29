package com.doubletapp.psbhack.screens.service_connection

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.main_npd.MainNpdActivity
import com.doubletapp.psbhack.main_npd.RegTokenPreference
import com.doubletapp.psbhack.screens.my_flat.MyFlatActivity
import kotlinx.android.synthetic.main.dialog_congrats.*

class CongratsDialog : DialogFragment() {

    private val regPreference by lazy { RegTokenPreference() }

    companion object {

        const val TAG = "CongratsDialog"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_congrats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val background = ColorDrawable(Color.TRANSPARENT)
        val margin = 50
        dialog?.window?.setBackgroundDrawable(InsetDrawable(background, margin, 0, margin, 0))

        button.setOnClickListener {
            dialog?.dismiss()
            context?.let {
                regPreference.setRegFinished()
                startActivity(MainNpdActivity.getIntent(it, false))
                activity?.finish()
            }
        }
    }
}