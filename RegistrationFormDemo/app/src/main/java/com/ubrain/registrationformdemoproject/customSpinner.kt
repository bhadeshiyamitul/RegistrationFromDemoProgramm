package com.ubrain.registrationformdemoproject

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class customSpinner() :BaseAdapter()
{

    lateinit var context:Context
    lateinit var cityName:ArrayList<String>

    constructor(context:Context,cityName:ArrayList<String>):this()
    {
        this.context=context
        this.cityName=cityName
    }

    override fun getCount(): Int {
        return cityName.size
    }

    override fun getItem(position: Int): Any {
                return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view=LayoutInflater.from(context).inflate(R.layout.spinner_adapter,parent,false)

        var txtSpinner=view.findViewById<TextView>(R.id.txt_spinner_city)

        txtSpinner.text=cityName[position]
        return view


    }
}