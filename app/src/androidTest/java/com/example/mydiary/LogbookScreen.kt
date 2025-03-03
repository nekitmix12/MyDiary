package com.example.mydiary

import android.view.View
import com.example.mydiary.presentation.fragments.LogbookFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

internal object LogbookScreen : KScreen<LogbookScreen>() {
    override val layoutId: Int = R.layout.logbook_fragment
    override val viewClass: Class<*> = LogbookFragment::class.java

    val recycler = KRecyclerView(
        builder = { withId(R.id.logbook_recycle_view) },
        itemTypeBuilder = {
            itemType(::LabelItem)
            itemType(::CircleButtonItem)
            itemType(::EmotionItem)
        }
    )
}

internal class LabelItem(parent: Matcher<View>) : KRecyclerItem<LabelItem>(parent) {
    val textView = KTextView { withId(R.id.label_text) }
}

internal class ButtonItem(parent: Matcher<View>) : KRecyclerItem<ButtonItem>(parent) {
    val button = KButton { withId(R.id.button) }
}

internal class CircleButtonItem(parent: Matcher<View>) : KRecyclerItem<CircleButtonItem>(parent) {
    val progressBar = KView { withId(R.id.progressBarView) }
    val button = KButton { withId(R.id.imageButton) }
}

internal class EmotionItem(parent: Matcher<View>) : KRecyclerItem<EmotionItem>(parent) {
    val background = KView { withId(R.id.background) }
    val textDataTime = KView { withId(R.id.text_data_time) }
    val cardFilling = KView { withId(R.id.card_filling) }
    val emotionSrc = KView { withId(R.id.emotion_src) }
}