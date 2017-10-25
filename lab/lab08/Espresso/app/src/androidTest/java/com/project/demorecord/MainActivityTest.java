package com.project.demorecord;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest{

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void Test1() {
        SystemClock.sleep(3000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(3000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
        SystemClock.sleep(1500);
    }

    @Test
    public void Test2() {
        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(3000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        SystemClock.sleep(3000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
        SystemClock.sleep(1500);
    }

    @Test
    public void Test3() {
        SystemClock.sleep(3000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withId(R.id.textNotFound)).check(matches(withText("Not Found")));
        SystemClock.sleep(3000);
    }

    @Test
    public void Test4(){
        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(3000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        SystemClock.sleep(3000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
        SystemClock.sleep(1500);
    }

    //CASE: Ying 20
    @Test
    public  void Test5() {
        onView(allOf(withId(R.id.editTExtName))).perform(replaceText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        onView(allOf(withId(R.id.editTextAge))).perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(3000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1500);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(1500);
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName)).check(matches(withText("Ying")));
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textAge)).check(matches(withText("20")));
        SystemClock.sleep(3000);

    }

    //CASE Ladarat 20
    @Test
    public  void Test6() {
        onView(allOf(withId(R.id.editTExtName))).perform(replaceText("Ladarat"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        onView(allOf(withId(R.id.editTextAge))).perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(3000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1500);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(1500);
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName)).check(matches(withText("Ladarat")));
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textAge)).check(matches(withText("20")));
        SystemClock.sleep(3000);
    }

    //CASE Somkait 80
    @Test
    public  void Test7() {
        onView(allOf(withId(R.id.editTExtName))).perform(replaceText("Somkait"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        onView(allOf(withId(R.id.editTextAge))).perform(replaceText("80"), closeSoftKeyboard());
        SystemClock.sleep(3000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1500);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(1500);
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName)).check(matches(withText("Somkait")));
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textAge)).check(matches(withText("80")));
        SystemClock.sleep(3000);
    }

    //CASE Prayoch 60
    @Test
    public  void Test8(){
        onView(allOf(withId(R.id.editTExtName))).perform(replaceText("Prayoch"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        onView(allOf(withId(R.id.editTextAge))).perform(replaceText("60"), closeSoftKeyboard());
        SystemClock.sleep(3000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1500);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(1500);
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName)).check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textAge)).check(matches(withText("60")));
        SystemClock.sleep(3000);
    }
}
