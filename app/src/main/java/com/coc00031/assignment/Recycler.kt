package com.coc00031.assignment

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.find

class Recycler(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal val number = itemView.find<TextView>(R.id.unit_number)
    internal val string = itemView.find<TextView>(R.id.unit_string)
    internal val symmetry = itemView.find<TextView>(R.id.unit_symbol)
}