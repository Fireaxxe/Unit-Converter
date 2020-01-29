package com.coc00031.assignment

import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.navigationIconResource
import org.jetbrains.anko.appcompat.v7.titleResource
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.verticalLayout

class SettingsUI : AnkoComponent<Activity> {
    override fun createView(ui: AnkoContext<Activity>) = with(ui) {
        verticalLayout {
            appBarLayout {
                toolbar {
                    navigationIconResource = R.drawable.icon_back_arrow_button //back icon when entered to Settings
                    setNavigationOnClickListener { owner.finish() }
                    titleResource = R.string.other_settings
                }
            }
            frameLayout { id = R.id.container }
        }
    }
}