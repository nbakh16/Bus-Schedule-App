package com.nbakh.busschedule.customdialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nbakh.busschedule.R

class MyAlertDialog(
    val icon: Int = R.drawable.ic_baseline_info,
    val title: String,
    val message: String,
    val posButtonText: String,
    val negButtonText: String,
    val posButtonCallback: () -> Unit
    ) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity()).apply {
            setIcon(icon)
            setTitle(title)
            setMessage(message)
            setPositiveButton(posButtonText) { dialog, which ->
                posButtonCallback()
            }
            setNegativeButton(negButtonText, null)
        }

        val dialog : AlertDialog = builder.create()
        return dialog
    }

}