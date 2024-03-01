package com.example.calculatorapp

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import org.hamcrest.Matchers.anything

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.calculatorapp", appContext.packageName)
    }

    @Test
    fun checkSum() {
        val listAdd1 = arrayOf("6", "7", "8")
        val listAdd2 = arrayOf("7", "8", "9")
        val listSum = arrayOf("13.0", "15.0", "17.0")
        for(i in 0..2) {
            onView(withId(R.id.Number1)).perform(
                ViewActions.typeText(listAdd1[i])
            )
            onView(withId(R.id.Number2)).perform(
                ViewActions.typeText(listAdd2[i])
            )
            onView(withId(R.id.PlusButton)).perform(
                click()
            )

            onView(withId(R.id.FinalNumber)).check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        listSum[i]
                    )
                )
            )
            onView(withId(R.id.Number1)).perform(
                clearText()
            )
            onView(withId(R.id.Number2)).perform(
                clearText()
            )
        }
    }

    @Test
    fun checkDiff() {
        onView(withId(R.id.Number1)).perform(
            ViewActions.typeText("4")
        )
        onView(withId(R.id.Number2)).perform(
            ViewActions.typeText("1")
        )
        onView(withId(R.id.MinusButton)).perform(
            click()
        )
        onView(withId(R.id.FinalNumber)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    "3.0"
                )
            )
        )
    }

    @Test
    fun historyButtonTest() {
        Intents.init()

        onView(withId(R.id.History)).perform(
            click()
        )

        intended(hasComponent(MainActivity2::class.java.name))

        Intents.release()
    }

    @Test
    fun historyTextCheck() {
        onView(withId(R.id.Number1)).perform(
            ViewActions.typeText("5")
        )
        onView(withId(R.id.Number2)).perform(
            ViewActions.typeText("9")
        )
        onView(withId(R.id.PlusButton)).perform(
            click()
        )
        onView(withId(R.id.History)).perform(
            click()
        )

        onData(anything()).atPosition(0).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    "5 + 9 = 14.0\n"
                )
            )
        )
    }

    @Test
    fun backButtonFunctionality() {
        onView(withId(R.id.History)).perform(
            click()
        )

        Intents.init()

        onView(withId(R.id.Back)).perform(
            click()
        )

        intended(hasComponent(MainActivity::class.java.name))
        Intents.release()
    }
}