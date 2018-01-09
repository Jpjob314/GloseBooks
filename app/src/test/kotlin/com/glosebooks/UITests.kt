package com.glosebooks

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.test.suitebuilder.annotation.LargeTest
import android.view.View

import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat

/**
 * Created by Julien on 09/01/2018.
 */
@RunWith(AndroidJUnit4::class)
class UITests {
    @Rule
    var mActivityRule = ActivityTestRule(
            RootActivity::class.java)

    @Test
    fun checkRecyclerViewItems() {
        onView(withId(1)).check(RecyclerViewItemCountAssertion(0))
    }

    inner class RecyclerViewItemCountAssertion : ViewAssertion {
        private val matcher: Matcher<Int>

        constructor(expectedCount: Int) {
            this.matcher = `is`(expectedCount)
        }

        constructor(matcher: Matcher<Int>) {
            this.matcher = matcher
        }

        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter.itemCount, matcher)
        }
    }
}


