package com.doubletapp.psbhack.flats_arenda

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.extensions.addOnTextChangedListener
import com.doubletapp.psbhack.extensions.setupActionBar
import com.doubletapp.psbhack.screens.my_flat.InfoFragment
import kotlinx.android.synthetic.main.fragment_add_flat.*
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.SpannableString
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private val RC_IMAGE_CAPTURE = 101
private val PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 7

class FlatsAddFlatFragment : Fragment() {

    companion object {
        const val ARG_FLAT = "flat"
        @JvmStatic fun newInstance(flat: FlatModel?) = FlatsAddFlatFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_FLAT, flat)
            }
        }
    }

    var oldFlat: FlatModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_flat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repo = FlatsRepo()

        setupActionBar(R.string.add_flat, true, R.id.toolbar)

        city_edit.addOnTextChangedListener {
            checkEnabled()
            if (it.isNotBlank())
                city_hint.visibility = View.VISIBLE
            else
                city_hint.visibility = View.INVISIBLE
        }

        address_edit.addOnTextChangedListener {
            checkEnabled()
            if (it.isNotBlank())
                address_hint.visibility = View.VISIBLE
            else
                address_hint.visibility = View.INVISIBLE
        }

        date_leave_edit.addOnTextChangedListener {
            if (it.isNotBlank())
                date_leave_hint.visibility = View.VISIBLE
            else
                date_leave_hint.visibility = View.INVISIBLE
        }

        fio_edit.addOnTextChangedListener {
            if (it.isNotBlank())
                fio_hint.visibility = View.VISIBLE
            else
                fio_hint.visibility = View.INVISIBLE
        }

        cost_edit.addOnTextChangedListener {
            checkEnabled()
            if (it.isNotBlank())
                cost_hint.visibility = View.VISIBLE
            else
                cost_hint.visibility = View.INVISIBLE
        }

        birth_edit.addOnTextChangedListener {
            if (it.isNotBlank())
                birth_hint.visibility = View.VISIBLE
            else
                birth_hint.visibility = View.INVISIBLE
        }

        passp_edit.addOnTextChangedListener {
            if (it.isNotBlank())
                passp_hint.visibility = View.VISIBLE
            else
                passp_hint.visibility = View.INVISIBLE
        }

        val citySpan = SpannableString("* "+getString(R.string.city))

        context?.let {
            citySpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(it, R.color.red)),
                    0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            citySpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(it, R.color.brown_grey)),
                    2, citySpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            city_edit.hint = citySpan
        }

        val addressSpan = SpannableString("* "+getString(R.string.address))

        context?.let {
            addressSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(it, R.color.red)),
                    0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            addressSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(it, R.color.brown_grey)),
                    2, addressSpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            address_edit.hint = addressSpan
        }

        val costSpan = SpannableString("* "+getString(R.string.cost2))

        context?.let {
            costSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(it, R.color.red)),
                    0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            costSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(it, R.color.brown_grey)),
                    2, costSpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            cost_edit.hint = costSpan
        }

        val stateSpan = SpannableString("* "+getString(R.string.state))

        context?.let {
            stateSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(it, R.color.red)),
                    0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            stateSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(it, R.color.brown_grey)),
                    2, stateSpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            state_edit.text = stateSpan
        }

        path_drop.setOnClickListener {
            val choice = arrayOf(getString(R.string.rent),
                    getString(R.string.soon_free),
                    getString(R.string.free))

            context?.let {
                AlertDialog.Builder(it)
                        .setTitle(R.string.choose_status)
                        .setItems(choice) { _, item ->
                            state_edit.text = choice[item]
                            checkEnabled()
                        }
                        .setNegativeButton("Отмена") { _, _ -> }
                        .create()
                        .show()
            }

        }

        val calendar = Calendar.getInstance()

        val sdf = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())

        val dateSetListener = { _: View, year: Int, monthOfYear : Int, dayOfMonth: Int ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            date_leave_edit.setText(sdf.format(calendar.time))
        }

        val dateSetListener2 = { _: View, year: Int, monthOfYear : Int, dayOfMonth: Int ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            birth_edit.setText(sdf.format(calendar.time))
        }

        context?.let {
            date_leave_edit.setOnClickListener { view ->
                DatePickerDialog(
                        it,
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            birth_edit.setOnClickListener { view ->
                DatePickerDialog(
                        it,
                        dateSetListener2,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
        setEnabled(false)

        val flat = arguments?.getParcelable<FlatModel>(InfoFragment.ARG_FLAT)
        if (flat != null) {
            city_edit.setText(flat.city)
            address_edit.setText(flat.address)
            state_edit.setText(flat.status)
            cost_edit.setText(flat.cost)
            fio_edit.setText(flat.arendatorName)
            date_leave_edit.setText(flat.date)
            passp_edit.setText(flat.arendatorPassport)
            birth_edit.setText(flat.arandatorDate)
            oldFlat = flat
            toolbar.setTitle(R.string.edit_flat)
            next_btn.setText(R.string.edit)
        }

        next_btn.setOnClickListener {

            if (oldFlat != null) {
                repo.changeFlat(oldFlat, FlatModel(
                        city = city_edit.text.toString(),
                        address = address_edit.text.toString(),
                        cost = cost_edit.text.toString(),
                        status = state_edit.text.toString(),
                        date = date_leave_edit.text.toString(),
                        arendatorName = fio_edit.text.toString(),
                        arandatorDate = birth_edit.text.toString(),
                        arendatorPassport = passp_edit.text.toString()
                ))
            } else {
                repo.putFlat(FlatModel(
                        city = city_edit.text.toString(),
                        address = address_edit.text.toString(),
                        cost = cost_edit.text.toString(),
                        status = state_edit.text.toString(),
                        date = date_leave_edit.text.toString(),
                        arendatorName = fio_edit.text.toString(),
                        arandatorDate = birth_edit.text.toString(),
                        arendatorPassport = passp_edit.text.toString()
                ))
            }

            activity?.onBackPressed()
        }

    }

    private fun checkEnabled()  {
        if (city_edit.text.isNotBlank() &&
                address_edit.text.isNotBlank() &&
                cost_edit.text.isNotBlank() &&
                state_edit.text.toString() != "* " + getString(R.string.state))
            setEnabled(true)
        else
            setEnabled(false)
    }

    private fun setEnabled(enabled: Boolean) {
        next_btn.isEnabled = enabled
        val context = context ?: return
        if (enabled)
            next_btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        else
            next_btn.setTextColor(ContextCompat.getColor(context, R.color.brown_grey))
    }
}