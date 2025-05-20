package com.example.mydiary

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mydiary.presentation.features.logbook.LogbookFragment
import com.example.mydiary.presentation.features.notes.NotesFragment
import com.example.mydiary.screens.LogbookScreen
import com.example.mydiary.screens.NotesScreen
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
internal class NotesTest {
    val navController = mock(NavController::class.java)
    @Test
    fun displayedTest() {
        launchFragment {
            NotesScreen {
                recycler {
                    childAt<ExitItem>(0) {
                        button.isDisplayed()
                        text.isDisplayed()
                        text.hasText(R.string.note)
                    }
                    childAt<EmotionItem>(position = 1) {
                        background.isDisplayed()
                        textDataTime.isDisplayed()
                        cardFilling.isDisplayed()
                        emotionSrc.isDisplayed()
                    }
                    childAt<QuestionsBlockItem>(2) {
                        label.isDisplayed()
                        flexBox.isDisplayed()
                    }
                    childAt<QuestionsBlockItem>(3) {
                        label.isDisplayed()
                        flexBox.isDisplayed()
                    }
                    childAt<QuestionsBlockItem>(4) {
                        label.isDisplayed()
                        flexBox.isDisplayed()

                    }
                }
                button {
                    isDisplayed()
                }
            }
        }
    }

    @Test
    fun goBack() {

            val fragment =  launchFragmentInContainer(themeResId = R.style.Theme_MyDiary) {
                LogbookFragment().apply {
                    viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                        if (viewLifecycleOwner != null) {
                            navController.setGraph(R.navigation.bottom_nav_graph)
                            Navigation.setViewNavController(requireView(), navController)
                        }
                    }
                }
            }
            Thread.sleep(30000)
            LogbookScreen {
                recycler {
                    childAt<EmotionItem>(position = 0) {
                        background.isDisplayed()
                        textDataTime.isDisplayed()
                        cardFilling.isDisplayed()
                        emotionSrc.isDisplayed()
                        rootView.click()
                    }
                }
            }
            val fragment2 =  launchFragmentInContainer(themeResId = R.style.Theme_MyDiary) {
                NotesFragment().apply {
                    viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                        if (viewLifecycleOwner != null) {
                            navController.setGraph(R.navigation.bottom_nav_graph)
                            Navigation.setViewNavController(requireView(), navController)
                        }
                    }
                }
            }
            NotesScreen {
                button {
                    click()
                }
            }

    }

    private fun launchFragment(testBlock: () -> Unit) {



        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_MyDiary) {
            NotesFragment().apply {
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