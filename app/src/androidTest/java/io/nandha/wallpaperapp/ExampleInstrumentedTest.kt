package io.nandha.wallpaperapp

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.nandha.wallpaperapp.ui.mainactivity.ImageItemAdapter
import io.nandha.wallpaperapp.ui.mainactivity.MainActivity
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get: Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testClickOnItem() {
        goToPosition(1)
        checkCurrentPositionBasedOnTag("Messier 20 and 21")
    }

    @Test
    fun testPreviousButton() {
        goToPosition(2)
        onView(withId(R.id.previous)).perform(click()).perform(click()).perform(
            click()
        ).perform(click()).perform(click())
        checkCurrentPositionBasedOnTag("M33: The Triangulum Galaxy")
    }

    @Test
    fun testNextButton() {
        goToPosition(10)
        onView(withContentDescription(R.string.next)).perform(click()).perform(click())
            .perform(click()).perform(click()).perform(click()).perform(click()).perform(click())
            .perform(click()).perform(click()).perform(click()).perform(click()).perform(click())
            .perform(click()).perform(click()).perform(click()).perform(click())
            .check(matches(isDisplayed()))
        checkCurrentPositionBasedOnTag("Starburst Galaxy M94 from Hubble")
    }

    private fun checkCurrentPositionBasedOnTag(tagText: String) {
        onView(
            allOf(
                withId(R.id.expand),
                withTagValue(Matchers.`is`(tagText))
            )
        ).check(
            matches(isDisplayed())
        ).perform(click())
        onView(withId(R.id.title)).check(matches(withText(containsString(tagText))))
    }

    private fun goToPosition(position: Int) {
        onView(withId(R.id.imageGrid)).perform(
            actionOnItemAtPosition<ImageItemAdapter.ViewHolder>(
                position,
                click()
            )
        )
    }
}