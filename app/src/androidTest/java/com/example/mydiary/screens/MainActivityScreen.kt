package com.example.mydiary.screens

import com.example.mydiary.R
import com.example.mydiary.presentation.MainActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView

internal object MainActivityScreen : KScreen<MainActivityScreen>() {
    override val layoutId: Int = R.layout.main_screen
    override val viewClass: Class<*> = MainActivity::class.java

    val logbookButton = KView{withId(R.id.logbook)}
    val statisticButton = KView{withId(R.id.statistics)}
    val settingsButton = KView{withId(R.id.settings)}

}