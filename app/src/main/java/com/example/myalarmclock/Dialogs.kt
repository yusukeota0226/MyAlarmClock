package com.example.myalarmclock

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.*

class SimpleAlertDialog : DialogFragment(){

    interface OnClickListener{
        fun onPositiveClick()
        fun onNegativeClick()
    }

    private lateinit var listener: OnClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is SimpleAlertDialog.OnClickListener){
            listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = context
        if(context == null)
            return super.onCreateDialog(savedInstanceState)
        val builder = AlertDialog.Builder(context).apply {
            setMessage("時間になりました！")
            setPositiveButton("起きる"){ dialog, which ->
                listener.onPositiveClick()
//                Toast.makeText(context, "起きるがクリックされました", Toast.LENGTH_SHORT).show()
//                context.toast("起きるがクリックされました")
            }
            setNegativeButton("あと5分"){ dialog, which ->
                listener.onNegativeClick()
//                Toast.makeText(context, "あと5分がクリックされました", Toast.LENGTH_SHORT).show()
//                dialog, which -> context.toast("あと5分がクリックされました")
            }
        }
        return builder.create()
    }
}

class DatePickerFragment : DialogFragment(),
        DatePickerDialog.OnDateSetListener{
    interface OnDateSelectedListener{
        fun onSelected(year: Int, month: Int, date: Int)
    }

    private lateinit var listener: OnDateSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnDateSelectedListener){
            listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val date = c.get(Calendar.DATE)
        return DatePickerDialog(
            this,
            year,
            month,
            date
        )
//        return DatePickerDialog(context, this, year, month, date)
    }

    private fun DatePickerDialog(
        datePickerFragment: DatePickerFragment,
        year: Int,
        month: Int,
        date: Int
    ): Dialog {
        TODO("Not yet implemented")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, date: Int) {
        listener.onSelected(year, month, date)
    }
}