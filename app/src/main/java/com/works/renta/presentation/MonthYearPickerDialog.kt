package com.works.renta.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.format.DateFormat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.works.renta.databinding.FragmentDatepickerBinding
import java.util.*


class MonthYearPickerDialog(private val periodDate: Long): DialogFragment() {

    private val MIN_YEAR = 2020
    private val MAX_YEAR = 2099
    private val MIN_MONTH = 0
    private val MAX_MONTH = 11
    private val calendar: Calendar = Calendar.getInstance()

    private lateinit var binding:FragmentDatepickerBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDatepickerBinding.inflate(layoutInflater)
        val arr = fillMonths()
        val listener = DialogInterface.OnClickListener{_, which ->
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_PERIOD to getTime()))
        }

        calendar.time = Date(periodDate)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        binding.pickerMonth.apply {
            minValue = MIN_MONTH
            maxValue = MAX_MONTH
            value = month
            displayedValues = arr
        }

        binding.pickerYear.apply {
            minValue = MIN_YEAR
            maxValue = MAX_YEAR
            value = year
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setCancelable(true)
            .setTitle("Выберите период")
            .setPositiveButton("Выбор", listener)
            .create()
    }

    private fun getTime(): Any? {
        return calendar.apply {
            set(Calendar.YEAR, binding.pickerYear.value)
            set(Calendar.MONTH, binding.pickerMonth.value)
            set(Calendar.DAY_OF_MONTH, 1)
        }.time.time
    }

    private fun fillMonths(): Array<String>? {
        calendar.set(Calendar.MONTH, 0)
        var months = arrayListOf<String>()
        for (i in 1..12) {
            months.add(DateFormat.format("MMM", calendar.time).toString()
                .let{it.replaceFirstChar(Char::titlecase)})
            calendar.add(Calendar.MONTH, 1);
        }

        return months.toTypedArray()
    }

    companion object{
        val TAG = MonthYearPickerDialog::class.java.simpleName
        val REQUEST_KEY = "$TAG:defaultRequestKey"
        val KEY_PERIOD = "PERIOD"
    }
}