package com.thoughtctl.utils

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher

class DelayedTextWatcher(private val onTextChanged: (String) -> Unit, private val delayMillis: Long = 1000) :
    TextWatcher {

    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        // Cancel any previously scheduled search
        runnable?.let { handler.removeCallbacks(it) }

        // Schedule a new search after the specified delay
        val searchText = editable.toString()
        runnable = Runnable {
            onTextChanged(searchText)
        }
        handler.postDelayed(runnable!!, delayMillis)
    }
}