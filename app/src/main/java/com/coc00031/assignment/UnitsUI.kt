package com.coc00031.assignment

import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.Gravity.END
import android.view.Gravity.START
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class UnitsUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        /**
         * This class is the same as MainUI, but this time its the design for the UnitsUI
         */
        linearLayout {

            verticalLayout {
                gravity = START
                padding = dip(16)
                textView {
                    id = R.id.unit_string
                    maxLines = 1
                    textAppearance = R.style.TextAppearance_AppCompat_Body2
                }.lparams(width = wrapContent, height = wrapContent)

                textView {
                    id = R.id.unit_symbol
                    maxLines = 1
                    textAppearance = R.style.TextAppearance_AppCompat_Body1
                }.lparams(width = wrapContent, height = wrapContent)
            }
            lparams(width = matchParent, height = wrapContent)
            backgroundResource = attr(R.attr.selectableItemBackground)
            gravity = Gravity.CENTER_VERTICAL
            minimumHeight = dip(72)
            orientation = LinearLayout.HORIZONTAL

            textView {

                id = R.id.unit_number
                ellipsize = TextUtils.TruncateAt.MARQUEE
                marqueeRepeatLimit = -1 // repeat indefinitely
                maxLines = 1
//                padding = dip(16)
                padding = dip(16)
                textAppearance = R.style.TextAppearance_AppCompat_Subhead
                gravity = END

            }.lparams(width = 0, height = wrapContent, weight = 1f)
        }
    }

    private fun AnkoContext<*>.attr(value: Int): Int {
        return TypedValue().let { ctx.theme.resolveAttribute(value, it, true); it.resourceId }
    }
}