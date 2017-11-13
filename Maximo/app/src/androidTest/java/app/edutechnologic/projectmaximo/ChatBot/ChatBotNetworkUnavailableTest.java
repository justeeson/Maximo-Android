package app.edutechnologic.projectmaximo.ChatBot;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.Assert;

/**
 * Tests for the chat bot when we don't have network connectivity.
 * Important: Must make sure that you manually turn off the network
 * in the emulator or device when running the tests.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ChatBotNetworkUnavailableTest {

    @Rule
    public ActivityTestRule<ChatBotActivity> mActivityTestRule = new ActivityTestRule<>(ChatBotActivity.class, true, true);

    @Test
    public void openActivityTest() {
        ChatBotActivity chatBotActivity = mActivityTestRule.getActivity();
        mActivityTestRule.launchActivity(new Intent());

        Assert.assertNotNull(chatBotActivity);
    }
}
