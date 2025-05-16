package com.example.mydiary

import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mydiary.screens.EntranceScreen
import com.example.mydiary.screens.MainActivityScreen
import com.example.mydiary.presentation.entrance_activity.EntranceActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class EntranceActivityTest : TestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(EntranceActivity::class.java)

    @Test
    fun displayedTest() {
        EntranceScreen {
            button.isDisplayed()
            background.isDisplayed()
            label.isDisplayed()
            button.isClickable()
        }
    }

    @Test
    fun switchingTest() {
        EntranceScreen{
            button.click()
        }
        MainActivityScreen{
            isDisplayed()
        }
    }
}