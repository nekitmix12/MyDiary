package com.example.mydiary

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.mydiary.presentation.features.settings.SettingsFragment
import com.example.mydiary.screens.BottomSheetScreen
import com.example.mydiary.screens.SettingsScreen
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
internal class SettingsFragmentTest {
    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun displayedTest() {
        launchFragment {
            SettingsScreen {
                recycler {
                    isDisplayed()
                    childAt<LabelItem>(position = 10) {
                        textView.isDisplayed()
                        textView.hasText(R.string.settings)
                    }
                    childAt<ProfileItem>(position = 20) {
                        image.isDisplayed()
                        text.isDisplayed()
                    }
                    childAt<RemindItem>(position = 30) {
                        text.isDisplayed()
                        image.isDisplayed()
                    }

                    childAt<ButtonItem>(position = 5) {
                        button.isDisplayed()
                    }

                    childAt<SettingsParamItem>(position = 2) {
                        icon.isDisplayed()
                        text.isDisplayed()
                        switch.isDisplayed()
                        switch.isClickable()
                    }
                    childAt<SettingsParamItem>(position = 5) {
                        icon.isDisplayed()
                        text.isDisplayed()
                        switch.isDisplayed()
                        switch.isClickable()
                    }

                    childAt<ButtonItem>(position = 5) {
                        button.isDisplayed()
                        button.isClickable()
                        button.click()
                    }
                }
            }
            BottomSheetScreen {
                textRemind.isDisplayed()
                hourText.isDisplayed()
                button.isDisplayed()
                minuteText.isDisplayed()
                Thread.sleep(3000)
                hourText.click()
                Thread.sleep(30000)

                //button.click()
            }

        }
    }


    private fun launchFragment(testBlock: () -> Unit) {

        val navController = mock(NavController::class.java)

        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_MyDiary) {
            SettingsFragment().apply {
                viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        navController.setGraph(R.navigation.bottom_nav_graph)
                        Navigation.setViewNavController(requireView(), navController)
                    }
                }
            }
        }

        testBlock()
    }
}