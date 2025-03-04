package com.example.mydiary

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mydiary.presentation.fragments.LogbookFragment
import com.example.mydiary.screens.LogbookScreen
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
internal class LogbookFragmentTest {

    @Test
    fun displayedTest() {
        launchFragment() {
            LogbookScreen {
                recycler {
                    childAt<LabelItem>(position = 0) {
                        textView.isDisplayed()
                        textView.hasText(R.string.what_are_you_listening_now)
                    }
                    childAt<CircleButtonItem>(position = 10) {
                        //спросить почему при wrap_content тест падал и почему позиция ни на что не влияет
                        button.isDisplayed()
                        button.isVisible()
                        progressBar.isDisplayed()
                        button.isClickable()
                    }
                    childAt<EmotionItem>(position = 2) {
                        background.isDisplayed()
                        textDataTime.isDisplayed()
                        cardFilling.isDisplayed()
                        emotionSrc.isDisplayed()
                    }

                    childAt<EmotionItem>(position = 3) {
                        background.isDisplayed()
                        textDataTime.isDisplayed()
                        cardFilling.isDisplayed()
                        emotionSrc.isDisplayed()
                    }

                    childAt<EmotionItem>(position = 0) {
                        background.isDisplayed()
                        textDataTime.isDisplayed()
                        cardFilling.isDisplayed()
                        emotionSrc.isDisplayed()
                    }

                    childAt<EmotionItem>(position = 1) {
                        rootView.click()
                        background.isDisplayed()
                        textDataTime.isDisplayed()
                        cardFilling.isDisplayed()
                        emotionSrc.isDisplayed()
                    }
                    childAt<TopBarItem>(position = 0) {
                        flexboxLayout.isDisplayed()
                    }
                    swipeUp()
                    swipeDown()
                }
            }


        }


    }

    @Test
    fun swipeTest() {
        launchFragment {
            LogbookScreen {
                recycler {
                    swipeUp()
                    swipeUp()
                    swipeUp()
                    swipeDown()
                }
            }
        }
    }

    private fun launchFragment(testBlock: () -> Unit) {

        val navController = mock(NavController::class.java)

        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_MyDiary) {
            LogbookFragment().apply {
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