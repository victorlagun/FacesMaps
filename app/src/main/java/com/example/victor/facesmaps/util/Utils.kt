package com.example.victor.facesmaps.util

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun alert(context: Context, message: String) {
    AlertDialog.Builder(context)
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ -> dialog.cancel() }
        .show()
}