package com.example.mydiary.screens

import com.example.mydiary.R
import com.example.mydiary.presentation.features.BottomSheetFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

internal object BottomSheetScreen : KScreen<BottomSheetScreen>() {
    override val layoutId: Int = R.layout.setting_bottom_sheet
    override val viewClass: Class<*> = BottomSheetFragment::class.java

    val textRemind = KTextView { withId(R.id.textView2) }
    val hourText = KTextView { withId(R.id.hour_text) }
    val minuteText = KTextView { withId(R.id.minute_text) }
    val button = KButton { withId(R.id.bottom_sheet_save_button) }
}
