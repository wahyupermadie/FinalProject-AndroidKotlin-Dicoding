package com.example.wahyupermadi.latihanlayout

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.support.test.espresso.IdlingRegistry
import com.example.wahyupermadi.latihanlayout.R.id.*
import com.example.wahyupermadi.latihanlayout.utils.EspressoIdlingResource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTesting{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource().getIdlingResource())
    }

    @Test
    fun MainTesting(){

        onView(withId(rv_before)).check(matches(isDisplayed()))
        onView(withId(rv_before)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(rv_before)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, ViewActions.click()))

        onView(withId(iv_detail_away)).check(matches(isDisplayed()))
        onView(withId(iv_detail_home)).check(matches(isDisplayed()))
        onView(withId(tv_detail_match_date)).check(matches(isDisplayed()))
        onView(withId(tv_detail_match_date)).check(matches(isDisplayed()))
        onView(withId(tv_home_detail_team_name)).check(matches(isDisplayed()))
        onView(withId(tv_home_detail_score)).check(matches(isDisplayed()))
        onView(withId(tv_home_detail_deff)).check(matches(isDisplayed()))
        onView(withId(tv_home_detail_fw)).check(matches(isDisplayed()))
        onView(withId(tv_home_detail_gk)).check(matches(isDisplayed()))
        onView(withId(tv_home_detail_mf)).check(matches(isDisplayed()))
        onView(withId(tv_home_detail_shots)).check(matches(isDisplayed()))
        onView(withId(tv_home_detail_sub)).check(matches(isDisplayed()))
        onView(withId(tv_away_detail_team_name)).check(matches(isDisplayed()))
        onView(withId(tv_away_detail_score)).check(matches(isDisplayed()))
        onView(withId(tv_away_detail_deff)).check(matches(isDisplayed()))
        onView(withId(tv_away_detail_fw)).check(matches(isDisplayed()))
        onView(withId(tv_away_detail_gk)).check(matches(isDisplayed()))
        onView(withId(tv_away_detail_goals)).check(matches(isDisplayed()))
        onView(withId(tv_away_detail_mf)).check(matches(isDisplayed()))
        onView(withId(tv_away_detail_shots)).check(matches(isDisplayed()))
        onView(withId(tv_away_detail_sub)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).perform(ViewActions.click())

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(viewpager_match)).perform(swipeLeft())

        onView(withId(rv_next)).check(matches(isDisplayed()))
        onView(withId(rv_next)).check(matches(isDisplayed()))
        onView(withId(rv_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(rv_next)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, ViewActions.click()))
    }
    private fun delay(){
        try {
            Thread.sleep(5000)
        }catch (e : InterruptedException){
            e.printStackTrace()
        }
    }

}