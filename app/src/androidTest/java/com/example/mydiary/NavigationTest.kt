package com.example.mydiary

import androidx.navigation.findNavController
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.mydiary.presentation.MainActivity
import com.example.mydiary.screens.AddEmotionScreen
import com.example.mydiary.screens.LogbookScreen
import com.example.mydiary.screens.MainActivityScreen
import com.example.mydiary.screens.NotesScreen
import com.example.mydiary.screens.SettingsScreen
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class NavigationTest {
    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun navigationTest() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        scenario.onActivity { activity ->
            val navController = activity.findNavController(R.id.fragmentContainerView)
            assertNotNull(navController)
        }

        LogbookScreen {
            recycler {
                childAt<CircleButtonItem>(0) {
                    button.click()
                }
            }
        }
        AddEmotionScreen {
            device.swipe(500, 1500, 500, 500, 10)
            buttonContinue.click()
        }
        NotesScreen {
            recycler {
                childAt<ExitItem>(0) {
                    button.click()
                }
            }
        }
        AddEmotionScreen {
            buttonBack.click()
        }

        LogbookScreen {
            recycler {
                childAt<EmotionItem>(4) {
                    rootView.click()
                }
            }
        }
        NotesScreen {
            button.click()
        }

        MainActivityScreen {
            settingsButton.click()
        }
        SettingsScreen {
            recycler.isDisplayed()
        }
        MainActivityScreen{
            settingsButton.click()
            logbookButton.click()
        }
    }

}