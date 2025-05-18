package com.example.mydiary

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.mydiary.screens.AddEmotionScreen
import com.example.mydiary.presentation.features.add_emotion.AddEmotionFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
internal class AddEmotionFragmentTest {
    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun displayedTest() {
        launchFragment {
            AddEmotionScreen {
                buttonBack.isDisplayed()
                background.isDisplayed()
                buttonContinue.isDisplayed()
                emotionText.isNotDisplayed()
                descriptionText.isDisplayed()
            }


        }


    }


    @Test
    fun swipeTest() {
        launchFragment {
            AddEmotionScreen {
                emotionText.isNotDisplayed()
                descriptionText.isDisplayed()
                buttonContinue.isNotClickable()
                device.swipe(500, 1500, 500, 500, 10)
                emotionText.isDisplayed()
                buttonContinue.isClickable()

            }
        }
    }

    private fun launchFragment(testBlock: () -> Unit) {

        val navController = mock(NavController::class.java)

        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_MyDiary) {
            AddEmotionFragment().apply {
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