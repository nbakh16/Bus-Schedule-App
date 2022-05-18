package com.nbakh.busschedule.customdialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.nbakh.busschedule.getFormattedDateTime
import java.util.*

class TimePickerFragment(val callback: (String) -> Unit) : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(requireActivity(), this, hour, minute, false)
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val c = Calendar.getInstance()
        c.set(0,0,0, hourOfDay, minute)
        val selectedTime = getFormattedDateTime(c.timeInMillis, "hh:mm aa")
        callback(selectedTime)
    }

}