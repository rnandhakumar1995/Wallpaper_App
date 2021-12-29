package io.nandha.wallpaperapp

import androidx.test.espresso.Espresso.onView
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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get: Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)

    private fun checkCurrentPositionBasedOnTag(tagText: String) {
        onView(
            allOf(
                withId(R.id.expand),
                withTagValue(Matchers.`is`(tagText))
            )
        ).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun testClickOnItem() {
        onView(withId(R.id.imageGrid)).perform(
            actionOnItemAtPosition<ImageItemAdapter.ViewHolder>(
                3,
                click()
            )
        )
        checkCurrentPositionBasedOnTag("A Distorted Sunrise Eclipse")
        testPreviousButton()
    }

    private fun testPreviousButton() {
        onView(withId(R.id.previous)).perform(click()).perform(click()).perform(
            click()
        ).perform(click()).perform(click())
        checkCurrentPositionBasedOnTag("M33: The Triangulum Galaxy")
    }
}