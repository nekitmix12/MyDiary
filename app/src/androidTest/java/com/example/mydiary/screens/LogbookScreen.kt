package com.example.mydiary.screens

import com.example.mydiary.CircleButtonItem
import com.example.mydiary.EmotionItem
import com.example.mydiary.LabelItem
import com.example.mydiary.R
import com.example.mydiary.TopBarItem
import com.example.mydiary.presentation.fragments.LogbookFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerView

internal object LogbookScreen : KScreen<LogbookScreen>() {
    override val layoutId: Int = R.layout.logbook_fragment
    override val viewClass: Class<*> = LogbookFragment::class.java

    val recycler = KRecyclerView(
        builder = { withId(R.id.logbook_recycle_view) },
        itemTypeBuilder = {
            itemType(::LabelItem)
            itemType(::CircleButtonItem)
            itemType(::EmotionItem)
            itemType(::TopBarItem)
        }
    )
}

