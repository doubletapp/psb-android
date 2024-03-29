package com.doubletapp.psbhack.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.addOnTextChangedListener(listener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            s?.let { listener(it.toString()) }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    })
}