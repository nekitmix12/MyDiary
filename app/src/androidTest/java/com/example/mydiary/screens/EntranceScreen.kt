package com.example.mydiary.screens

import com.example.mydiary.R
import com.example.mydiary.presentation.EntranceActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

internal object EntranceScreen : KScreen<EntranceScreen>() {
    override val layoutId: Int = R.layout.entrance
    override val viewClass: Class<*> = EntranceActivity::class.java

    val background = KView { withId(R.id.entrance_background) }
    val button = KButton { withId(R.id.button) }
    val label = KTextView { withId(R.id.textView) }
}