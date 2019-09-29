package com.doubletapp.psbhack.main_npd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.extensions.addOnTextChangedListener
import com.doubletapp.psbhack.extensions.setupActionBar
import com.doubletapp.psbhack.screens.service_connection.CongratsDialog
import kotlinx.android.synthetic.main.fragment_bill.*

class BillFragment  : Fragment() {
    companion object {
        @JvmStatic fun newInstance() = BillFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar(R.string.give_bill, true, R.id.toolbar)

        name_edit.addOnTextChangedListener {
            checkEnabled()
            if (it.isNotBlank())
                name_hint.visibility = View.VISIBLE
            else
                name_hint.visibility = View.INVISIBLE
        }

        payment_edit.addOnTextChangedListener {
            checkEnabled()
            if (it.isNotBlank())
                payment_hint.visibility = View.VISIBLE
            else
                payment_hint.visibility = View.INVISIBLE
        }

        sum_edit.addOnTextChangedListener {
            checkEnabled()
            if (it.isNotBlank())
                sum_hint.visibility = View.VISIBLE
            else
                sum_hint.visibility = View.INVISIBLE
        }

        phone_edit.addOnTextChangedListener {
            checkEnabled()
            if (it.isNotBlank())
                phone_hint.visibility = View.VISIBLE
            else
                phone_hint.visibility = View.INVISIBLE
        }

        email_edit.addOnTextChangedListener {
            checkEnabled()
            if (it.isNotBlank())
                email_hint.visibility = View.VISIBLE
            else
                email_hint.visibility = View.INVISIBLE
        }

        setEnabled(false)

        add_btn.setOnClickListener {
            BillDialog().show(childFragmentManager, BillDialog.TAG)
        }

    }

    private fun checkEnabled()  {
        if (name_edit.text.isNotBlank() &&
                payment_edit.text.isNotBlank() &&
                sum_edit.text.isNotBlank() &&
                email_edit.text.isNotBlank())
            setEnabled(true)
        else
            setEnabled(false)
    }

    private fun setEnabled(enabled: Boolean) {
        add_btn.isEnabled = enabled
        val context = context ?: return
        if (enabled)
            add_btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        else
            add_btn.setTextColor(ContextCompat.getColor(context, R.color.brown_grey))
    }
}