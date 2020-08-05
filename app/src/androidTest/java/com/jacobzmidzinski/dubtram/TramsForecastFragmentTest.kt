package com.jacobzmidzinski.dubtram

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.jacobzmidzinski.dubtram.presentation.ui.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.JVM)
class TramsForecastFragmentTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun launchFragmentAndVerifyUI() {
        onView(withId(R.id.refresh_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.contentLoader)).check(matches(isDisplayed()))
        onView(withId(R.id.tramsForecastList)).check(matches(not(isDisplayed())))
    }
}