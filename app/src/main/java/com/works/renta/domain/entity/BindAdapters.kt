package com.works.renta.domain.entity

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.inputmethod.InputMethod
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

@BindingAdapter("IntToString")
    fun setTextInt(editText: EditText, elect: Int){
        editText.setText(elect.toString())
    }

@InverseBindingAdapter(attribute = "IntToString")
fun getTextInt(view: EditText): Int? {
    val editTextString: String? = view.text?.toString()
        return if(editTextString == null || editTextString == "") 0 else editTextString.toString().toInt()
    }

@BindingAdapter("app:IntToStringAttrChanged")
fun setIntToStringChangedListener(editText: EditText, listener: InverseBindingListener) {
    editText.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            listener.onChange()
        }
    })
}

@BindingAdapter("FloatToString")
fun setTextFloat(editText: EditText, elect: Float){
    val separator =  DecimalFormatSymbols().apply {
        groupingSeparator = ' '
        decimalSeparator = '.'
    }
    editText.setText(DecimalFormat("###,##0.00", separator).format(elect))
}

@InverseBindingAdapter(attribute = "FloatToString")
fun getTextFloat(view: EditText): Float? {
    val editTextString: String? = view.text?.toString()
    return if(editTextString == null || editTextString == "") 0.0F else editTextString.toString().toFloat()
}

@BindingAdapter("app:FloatToStringAttrChanged")
fun setFloatToStringChangedListener(editText: EditText, listener: InverseBindingListener) {
    editText.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            listener.onChange()
        }
    })
}