package com.example.mydiary.screens

import com.example.mydiary.ButtonItem
import com.example.mydiary.LabelItem
import com.example.mydiary.ProfileItem
import com.example.mydiary.R
import com.example.mydiary.RemindItem
import com.example.mydiary.SettingsParamItem
import com.example.mydiary.presentation.fragments.setting_feature.SettingsFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerView

internal object SettingsScreen : KScreen<SettingsScreen>() {
    override val layoutId: Int = R.layout.settings_fragment
    override val viewClass: Class<*> = SettingsFragment::class.java
    val recycler = KRecyclerView(
        builder = {
            withId(R.id.settings_recycler)
        },
        itemTypeBuilder = {
            itemType(::LabelItem)
            itemType(::ProfileItem)
            itemType(::SettingsParamItem)
            itemType(::RemindItem)
            itemType(::ButtonItem)
        }
    )
}