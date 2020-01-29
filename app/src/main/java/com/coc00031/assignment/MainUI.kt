package com.coc00031.assignment

import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.design._CoordinatorLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainUI : AnkoComponent<MainActivity> {

    internal lateinit var sampleLayout: DrawerLayout
    internal lateinit var androidToolbar: Toolbar
    internal lateinit var orientationSaveText: EditText
    internal lateinit var selectedUnitName: TextView
    internal lateinit var selectedUnitSymbol: TextView
    internal lateinit var recView: RecyclerView
    internal lateinit var navView: NavigationView

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

        /**
         * This class includes the main design of the application using the sample_layout.
         * It could be created in the res - layout from the begging and have more control but it was preferable to manually hard code it here using Anko libraries
         */

        include<DrawerLayout>(R.layout.sample_layout) {
            sampleLayout = this
            coordinatorLayout { createToolbar(); createConverterLayout() }
            navView = navigationView {
                layoutParams = DrawerLayout.LayoutParams(wrapContent, matchParent, Gravity.START)
            }
        }
    }

    private fun _CoordinatorLayout.createToolbar() {
        androidToolbar = toolbar().lparams(width = matchParent, height = dimen(R.dimen.app_bar_height))
    }

    private fun _CoordinatorLayout.createConverterLayout() {
        cardView {
            verticalLayout { createInputField(); createDivider(); createConverterList() }
        }.lparams(width = matchParent, height = matchParent) {
            topMargin = dimen(R.dimen.card_view_margin_top) //top margin before input
        }
    }

    private fun _LinearLayout.createDivider() {
        view {
            backgroundResource = android.R.color.darker_gray //divider line colour
        }.lparams(width = matchParent, height = dip(5))//divider line under input
    }

    private fun _LinearLayout.createInputField() {
        /**
         * This method creates 2 layouts, one for the user input space and the results (right hand) and one with the units names and symbols (left hand)
         */

        linearLayout {
            verticalLayout {
                gravity = Gravity.START
                padding = dip(16)
                selectedUnitName = textView {
                    textAppearance = R.style.TextAppearance_AppCompat_Body2
                }.lparams(width = wrapContent, height = dip(0), weight = 1f)
                selectedUnitSymbol = textView {
                    textAppearance = R.style.TextAppearance_AppCompat_Body1
                }.lparams(width = wrapContent, height = dip(0), weight = 1f)
            }.lparams(width = wrapContent, height = matchParent)

            verticalLayout {
                gravity = Gravity.END
                padding = dip(16)
                orientationSaveText = editText {
                    backgroundResource = android.R.color.transparent // transparent colour for the line under input text
                    hintResource = R.string.enter_a_number_text // hint text saying "Please enter a number" on the input text
                    inputType = 12290 // to accept only decimal numbers
                    imeOptions = EditorInfo.IME_ACTION_DONE //the action key performs a "done" operation, meaning there is nothing more to input and the IME will be closed.
                    padding = dip(16) //input padding
                    textAppearance = R.style.TextAppearance_AppCompat_Large
                    gravity = Gravity.START
                }.lparams(width = matchParent, height = dip(0), weight = 1f)
            }.lparams(width = matchParent, height = wrapContent)
        }.lparams(width = matchParent, height = wrapContent)
    }

    private fun _LinearLayout.createConverterList() {
        recView = recyclerView().lparams(width = matchParent, height = matchParent)
    }
}