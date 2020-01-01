package com.jadgroup.urbankotlin;


import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import com.jadgroup.urbankotlin.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class MainActivityViewTest {

    @Rule
    public ActivityTestRule<MainActivity> scenarioRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickThumbsUpView() {
        onView(withId(R.id.txtView_thumbsUp)).perform(click());
    }

    @Test
    public void clickThumbsDownView() {
        onView(withId(R.id.txtView_thumbsDown)).perform(click());
    }

    @Test
    public void clickThumbsUpDownView() {
        onView(withId(R.id.txtView_thumbsUp)).perform(click());
        onView(withId(R.id.txtView_thumbsDown)).perform(click());
    }


    @Test
    public void SearchTextView() {
        onView(withId(R.id.editTextSearch)).perform(ViewActions.clearText())
                .perform(ViewActions.typeText("Atif"), closeSoftKeyboard());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.txtView_thumbsUp)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.txtView_thumbsDown)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
