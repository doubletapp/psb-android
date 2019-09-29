package com.doubletapp.psbhack.extensions

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView

fun EditText.onChange(action: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable?) { action(s?.toString().orEmpty()) }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}

fun EditText.onDoneClick(action: () -> Unit) {
    setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId: Int, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
            action.invoke()
            return@OnEditorActionListener true
        }
        return@OnEditorActionListener false
    })
}

fun EditText.showKeyboard() {
    requestFocus()
    (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

fun EditText.hideKeyboard() {
    (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        hideSoftInputFromWindow(windowToken, 0)
    }
}