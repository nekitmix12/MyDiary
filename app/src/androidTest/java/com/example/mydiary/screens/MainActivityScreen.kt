package com.example.mydiary.screens

import com.example.mydiary.R
import com.example.mydiary.presentation.MainActivity
import com.kaspersky.kaspresso.screens.KScreen

internal object MainActivityScreen : KScreen<MainActivityScreen>() {
    override val layoutId: Int = R.layout.main_screen
    override val viewClass: Class<*> = MainActivity::class.java


}