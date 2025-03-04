package com.example.mydiary

import android.view.View
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

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
    val rootView = KView { withMatcher(parent) }
    val background = KView { withId(R.id.background) }
    val textDataTime = KView { withId(R.id.text_data_time) }
    val cardFilling = KView { withId(R.id.card_filling) }
    val emotionSrc = KView { withId(R.id.emotion_src) }
}

internal class TopBarItem(parent: Matcher<View>) : KRecyclerItem<TopBarItem>(parent) {
    val flexboxLayout = KView { withId(R.id.top_logbook_bar_flexbox) }
}

internal class EmotionsByDayItem(parent: Matcher<View>) : KRecyclerItem<EmotionsByDayItem>(parent) {

}

internal class EmotionsCategoryItem(parent: Matcher<View>) :
    KRecyclerItem<EmotionsCategoryItem>(parent) {

}

internal class EmotionsIndicatorItem(parent: Matcher<View>) :
    KRecyclerItem<EmotionsIndicatorItem>(parent) {

}

internal class ExitItem(parent: Matcher<View>) : KRecyclerItem<ExitItem>(parent) {

}

internal class MoodyDuringDayItem(parent: Matcher<View>) :
    KRecyclerItem<MoodyDuringDayItem>(parent) {

}

internal class ProfileItem(parent: Matcher<View>) : KRecyclerItem<ProfileItem>(parent) {
    val image = KView { withId(R.id.shapeable_image_view) }
    val text = KTextView { withId(R.id.profile_name) }
}

internal class QuestionsBlockItem(parent: Matcher<View>) :
    KRecyclerItem<QuestionsBlockItem>(parent) {

}

internal class SettingsParamItem(parent: Matcher<View>) : KRecyclerItem<SettingsParamItem>(parent) {
    val icon = KImageView { withId(R.id.param_icon_z) }
    val text = KTextView { withId(R.id.param_text) }
    val switch = KView { withId(R.id.param_switch) }
}

internal class RemindItem(parent: Matcher<View>) : KRecyclerItem<RemindItem>(parent) {
    val text = KTextView {withId( R.id.remind_data) }
    val image = KView {withId (R.id.delete_remind )}
}