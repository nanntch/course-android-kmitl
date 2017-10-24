package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void Test1() {
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(5000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        SystemClock.sleep(5000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
        SystemClock.sleep(5000);
    }

    @Test
    public void Test2() {
        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        SystemClock.sleep(5000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        SystemClock.sleep(5000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
        SystemClock.sleep(5000);
    }

    @Test
    public void Test3() {
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

        onView(withId(R.id.textNotFound)).check(matches(withText("Not Found")));
        SystemClock.sleep(5000);
    }

    @Test
    public void Test4(){
        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        SystemClock.sleep(5000);

        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
        SystemClock.sleep(5000);
    }

    @Test
    public  void Test5() {
        //CASE Ying 20
        onView(allOf(withId(R.id.editTExtName))).perform(replaceText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.editTextAge))).perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(5000);
        //        onView(withText("Ying")).check(matches(isDisplayed()));
//        SystemClock.sleep(5000);
    }

    @Test
    public  void Test6() {
        //CASE Ladarat 20
        onView(allOf(withId(R.id.editTExtName))).perform(replaceText("Ladarat"), closeSoftKeyboard());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.editTextAge))).perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(5000);
    }

    @Test
    public  void Test7() {
        //CASE Somkait 80
        onView(allOf(withId(R.id.editTExtName))).perform(replaceText("Somkait"), closeSoftKeyboard());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.editTextAge))).perform(replaceText("80"), closeSoftKeyboard());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(5000);
    }

    @Test
    public  void Test8(){
        //CASE Prayoch 60
        onView(allOf(withId(R.id.editTExtName))).perform(replaceText("Prayoch"), closeSoftKeyboard());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.editTextAge))).perform(replaceText("60"), closeSoftKeyboard());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(5000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(5000);
    }



}
