package app.edutechnologic.projectmaximo.NetworkUnavailableTests;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
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

import app.edutechnologic.projectmaximo.Maximo;
import app.edutechnologic.projectmaximo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AttemptSendMessageWhenNetworkUnavailableTest {

    @Rule
    public ActivityTestRule<Maximo> mActivityTestRule = new ActivityTestRule<>(Maximo.class);

    @Test
    public void attemptSendMessageWhenNetworkUnavailableTest() {
        ViewInteraction appCompatButton = onView(
                allOf(ViewMatchers.withId(R.id.chat_nav_btn), withText("Chat"), withContentDescription("Chatbot"),
                        childAtPosition(
                                allOf(withId(R.id.chat_nav),
                                        childAtPosition(
                                                withId(R.id.navbar_scroll_layout),
                                                2)),
                                0)));
        appCompatButton.perform(scrollTo(), click());
        pressBack();

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.messageBox),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.messageBox),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.messageBox),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.messageBox),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.messageBox),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText5.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.messageBox),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText6.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.messageBox),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("hello, his i,"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.messageBox), withText("hello, his i,"),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("hello, his is a message,"));

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.messageBox), withText("hello, his is a message,"),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText9.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.sendButton), withText("Send"),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.messageBox), withText("Enter a message"),
                        childAtPosition(
                                allOf(withId(R.id.chat_message_entry),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                1),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
