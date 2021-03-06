package edu.gatech.seclass.partyplaylist;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<Register> mActivityTestRule = new ActivityTestRule<>(Register.class);

    @Test
    public void loginTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.to_loginpage), withText("Existing user? Login here"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username_field), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.username_field), isDisplayed()));
        appCompatEditText2.perform(replaceText("e"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.password_field), isDisplayed()));
        appCompatEditText3.perform(replaceText("e"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.email_field), isDisplayed()));
        appCompatEditText4.perform(replaceText("e@e.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login_button), withText("LOGIN"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.logout), withText("Logout."),
                        withParent(allOf(withId(R.id.activity_user_home),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatTextView2.perform(click());

    }

}
