package io.nandha.wallpaperapp

import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.viewpager2.widget.ViewPager2
import io.nandha.wallpaperapp.ui.mainactivity.ImageItemAdapter
import io.nandha.wallpaperapp.ui.mainactivity.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    fun atPosition(position: Int, itemMatcher: Matcher<View>): BoundedMatcher<View?, ViewPager2> {
        return object : BoundedMatcher<View?, ViewPager2>(ViewPager2::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: ViewPager2): Boolean {
                val viewHolder =
                    (view[0] as RecyclerView).findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                return itemMatcher.matches(viewHolder.itemView.findViewById(R.id.expand))
            }
        }
    }

    @get: Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testClickOnItem() {
        onView(withId(R.id.imageGrid)).perform(
            actionOnItemAtPosition<ImageItemAdapter.ViewHolder>(
                3,
                click()
            )
        )
        onView(allOf(withId(R.id.expand), withTagValue(Matchers.`is`("A Distorted Sunrise Eclipse")))).check(
            matches(isDisplayed())
        )
    }
}