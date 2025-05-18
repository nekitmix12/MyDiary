package com.example.mydiary.screens

import com.example.mydiary.R
import com.example.mydiary.presentation.features.add_emotion.AddEmotionFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KButton

object AddEmotionScreen : KScreen<AddEmotionScreen>() {
    override val layoutId: Int = R.layout.add_emotion_fragment
    override val viewClass: Class<*> = AddEmotionFragment::class.java

    val buttonBack = KButton{withId(R.id.exit_button)}
    val background = KView{withId(R.id.add_logs_field)}
    val emotionText = KView{withId(R.id.emotion_to_adding)}
    val descriptionText = KView{withId(R.id.description_emotion)}
    val buttonContinue = KButton{withId(R.id.add_button)}
}
