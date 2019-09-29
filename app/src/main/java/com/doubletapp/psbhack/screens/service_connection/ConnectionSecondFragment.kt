package com.doubletapp.psbhack.screens.service_connection

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.extensions.addFragment
import com.doubletapp.psbhack.extensions.addOnTextChangedListener
import com.doubletapp.psbhack.extensions.replaceFragment
import com.doubletapp.psbhack.extensions.setupActionBar
import kotlinx.android.synthetic.main.fragment_connection_second.*

class ConnectionSecondFragment : Fragment() {

    private val selectedItemsText = MutableLiveData<String>()
    private var regionChecked = false

    companion object {
        private const val SELECT_REQ_CODE = 422
        @JvmStatic fun newInstance() = ConnectionSecondFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_connection_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar(R.string.service_connect, true, R.id.toolbar)
        agree_text.movementMethod = LinkMovementMethod.getInstance()

        if (region_label.text != getString(R.string.region_activity))
            regionChecked = true

        phone_text.addOnTextChangedListener {
            phone_label.visibility = if (it.isNotBlank()) View.VISIBLE else View.INVISIBLE

            setButtonEnable(check_agreement.isChecked
                    && select_text.text != getString(R.string.activity_variants)
                    && phone_text.text.isNotBlank()
                    && regionChecked)
        }

        region_dropdown.setOnClickListener {
            val choice = arrayOf(getString(R.string.Moscow),
                    getString(R.string.Moscow_distr),
                    getString(R.string.Kalug_dis),
                    getString(R.string.Tatarstan))

            context?.let {
                AlertDialog.Builder(it)
                        .setTitle(R.string.choose_region)
                        .setItems(choice) { _, item ->
                            region_text.text = choice[item]
                            regionChecked = true
                            setButtonEnable(check_agreement.isChecked
                                    && select_text.text != getString(R.string.activity_variants)
                                    && regionChecked && phone_text.text.isNotBlank())
                        }
                        .setNegativeButton("Отмена") { _, _ -> }
                        .create()
                        .show()
            }

        }
        select_dropdown.setOnClickListener {
            val list: List<String> = when (select_text.text == getString(R.string.activity_variants)) {
                true -> emptyList()
                else -> select_text.text.split(", ")
            }
            val fragment = ConnectionThirdFragment.newInstance(list)
            fragment.setTargetFragment(this, SELECT_REQ_CODE)
            addFragment(fragment, null)
        }
        selectedItemsText.observe(viewLifecycleOwner, select_text::setText)

        next_btn.setOnClickListener {
            CongratsDialog().show(childFragmentManager, CongratsDialog.TAG)
        }

        check_agreement.setOnCheckedChangeListener { _, checked ->
            setButtonEnable(checked && select_text.text != getString(R.string.activity_variants)
                    && regionChecked && phone_text.text.isNotBlank()
            ) }

        setButtonEnable(false)
    }

    private fun setButtonEnable(enable: Boolean) {
        val context = context ?: return
        if (enable) {
            next_btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            next_btn.isEnabled = true
        } else {
            next_btn.setTextColor(ContextCompat.getColor(context , R.color.brown_grey))
            next_btn.isEnabled = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_REQ_CODE) {
            val selectedItems = data?.getStringArrayListExtra(ConnectionThirdFragment.ARGS_ACTIVITIES)
            selectedItemsText.value = when (selectedItems.isNullOrEmpty()) {
                true -> getString(R.string.activity_variants)
                else -> selectedItems.joinToString(", ")
            }
            setButtonEnable(check_agreement.isChecked &&
                    select_text.text != getString(R.string.activity_variants)
                    && regionChecked && phone_text.text.isNotBlank())
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}