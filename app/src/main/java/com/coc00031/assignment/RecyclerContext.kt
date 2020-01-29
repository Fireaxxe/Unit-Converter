package com.coc00031.assignment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext

class RecyclerContext(private val context: Context) : RecyclerView.Adapter<Recycler>() {

    internal var units = emptyList<UnitCalc>()
    internal var input = Double.NaN
        set(value) {
            field = value; notifyDataSetChanged()
        }
    internal lateinit var whenTap: (Int) -> Unit //when tab a unit from the results it will replace the current unit name with the new one
    internal lateinit var whenLongTap: (String) -> Unit //when long tab a unit from the results it will copy its value as a string
    override fun getItemCount() = units.size
    override fun onBindViewHolder(holder: Recycler, position: Int) {
        val unit = units[position]
        val output = unit.baseToSelf(context, input)
//        holder.number.text = "${output.toFloat()}"
        holder.number.text = "${output.toBigDecimal()}" // toBigDecimal: get rid of early Scientific Notation showing more digits on output
        holder.string.setText(unit.label)
        holder.symmetry.setText(unit.shortLabel)
        holder.itemView.setOnClickListener { whenTap(position) }
        holder.itemView.setOnLongClickListener { whenLongTap(output.toString()); true }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycler {
        return Recycler(UnitsUI().createView(AnkoContext.create(parent.context, parent)))
    }
}