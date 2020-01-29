package com.coc00031.assignment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.view.Gravity.*
import android.widget.EditText
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk21.listeners.textChangedListener

class MainActivity : AppCompatActivity() {

    companion object {
        private const val id_value = "com.coc00031.assignment.id_value"
        private const val input_value = "com.coc00031.assignment.input_value"
    }
    private val ui = MainUI()
    private var id: Int? = null
    private var position: Int? = null
    private val connection by lazy { RecyclerContext(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        setTheme()
        setAndroidToolbar()
        setMiniFeatures()
        setUnitMenu()
        init(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        refreshActionMenu()
        refreshRecView()
    }

    override fun onBackPressed() {
        when {
            ui.sampleLayout.isDrawerOpen(START) -> ui.sampleLayout.closeDrawers()
            else -> super.onBackPressed()
        }
    }

    private fun getValueInt(key: String, defValue: Int = 0): Int {
        return try {
            default.getInt(key, defValue)
        }
        catch (e: ClassCastException) {
            default.getString(key, defValue.toString()).toIntOrNull() ?: throw e
        }
    }

    private fun String.setValueInt(value: Int?) {
        value?.let { default.edit().putInt(this, it).apply() }
    }

    private val default get() = PreferenceManager.getDefaultSharedPreferences(this)!!

    override fun onSaveInstanceState(outState: Bundle) {
        id?.let { outState.putInt(id_value, it) }
        outState.putString(input_value, ui.orientationSaveText.text.toString())//it saves the user input when the screen rotates
        super.onSaveInstanceState(outState)
    }

    private fun init(savedInstanceState: Bundle?) {
        val defaultId = getValueInt(id_value).takeIf { Units.isIdSafe(it) } ?: R.id.unit_length
        if (savedInstanceState != null) {
            selectId(savedInstanceState.getInt(id_value, defaultId))
            ui.orientationSaveText.setText(savedInstanceState.getString(input_value))//it sets the user input when the screen rotates
        }
        else{
            selectId(R.id.unit_length) //when the application starts the instanceState is null and it will load nothing. With this, the "Length" unit will load first
        }

    }

    private fun selectId(@IdRes id: Int) {
        this.id = id
        id_value.setValueInt(id)
        refreshActionMenu()
        val item = Units.get(id) ?: return
        connection.units = Conversions.retrieveUnits(item)
        selectPosition(0)
        ui.androidToolbar.setBackgroundResource(R.color.light_orange) // set light orange colour for the androidToolbar above program
        ui.sampleLayout.setStatusBarBackground(R.color.deep_orange) // set deeper orange colour to the status bar of the application
    }

    private fun selectPosition(position: Int) {
        this.position = position
        refreshRecView()
        ui.selectedUnitName.setText(connection.units[position].label)
        ui.selectedUnitName.textSize = 17f // set text size for the selected unit name
        ui.selectedUnitName.setTypeface(null, Typeface.BOLD) // set bold to the text for the selected unit name
        ui.selectedUnitSymbol.setText(connection.units[position].shortLabel)
        ui.selectedUnitSymbol.textSize = 15f // set text size for the selected unit symbol
        ui.selectedUnitSymbol.setTypeface(null, Typeface.BOLD) // set bold to the text for the selected unit symbol


    }

    private fun setTheme() {
        AppCompatDelegate.setDefaultNightMode(getValueInt("night_day_theme", 1)) //set the default theme to day mode
    }

    /**
     * setAndroidToolbar function to customize the icons on the Toolbar (clear button and menu button)
     */
    private fun setAndroidToolbar() {
        ui.androidToolbar.inflateMenu(R.menu.clear_button)
        ui.androidToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.clear -> actionClear()
            }
            true
        }
        ui.androidToolbar.setNavigationIcon(R.drawable.icon_menu_hamburger)
        ui.androidToolbar.setNavigationOnClickListener { ui.sampleLayout.openDrawer(START)}
    }

    /**
     * setMiniFeatures manages to save the text while orientate, select new unit when tab and copy value when long tab.
     */
    private fun setMiniFeatures() {
        ui.orientationSaveText.afterTextChanged { refreshActionMenu(); refreshRecView() }
        connection.whenTap = { selectPosition(it) }
        connection.whenLongTap = { copyToClipboard(it) }
        ui.recView.adapter = connection
        ui.recView.layoutManager = LinearLayoutManager(this)
        ui.recView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    /**
     * Manages the side drawers when the unit menu opens
     */
    private fun setUnitMenu() {
        ui.navView.inflateMenu(R.menu.side_unit_menu)
        ui.navView.setNavigationItemSelectedListener {
            ui.sampleLayout.closeDrawers()
            when (it.itemId) {
                R.id.settings_menu -> startActivity<Activity>()
                else -> selectId(it.itemId)
            }
            true
        }
    }

    private fun actionClear() = ui.orientationSaveText.text.clear()

    private fun refreshActionMenu() {
        ui.androidToolbar.menu.findItem(R.id.clear).isVisible = ui.orientationSaveText.text.isNotEmpty()
    }

    private fun refreshRecView() {
        val num = ui.orientationSaveText.text.toString().toDoubleOrNull() ?: 0.0 //set 0.0 to all result values if the user input is null
        position?.let { connection.input = connection.units[it].selfToBase(this, num) }
    }

    private fun copyToClipboard(text: String) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.primaryClip = ClipData.newPlainText("conversion output", text)
        toast(R.string.clipboard_message)
    }

    private fun EditText.afterTextChanged(listener: (Editable?) -> Unit) {
        textChangedListener { afterTextChanged(listener) }
    }
}