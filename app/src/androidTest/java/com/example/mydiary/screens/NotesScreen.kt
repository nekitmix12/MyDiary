package com.example.mydiary.screens

import com.example.mydiary.EmotionItem
import com.example.mydiary.ExitItem
import com.example.mydiary.QuestionsBlockItem
import com.example.mydiary.R
import com.example.mydiary.presentation.features.notes.NotesFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton

object NotesScreen : KScreen<NotesScreen>() {
    override val layoutId: Int = R.layout.notes_fragment
    override val viewClass: Class<*> = NotesFragment::class.java

    val button = KButton { withId(R.id.button111) }
    val recycler = KRecyclerView({ withId(R.id.nodes_recycler) }, {
        itemType(::ExitItem)
        itemType(::EmotionItem)
        itemType(::QuestionsBlockItem)
    })
}