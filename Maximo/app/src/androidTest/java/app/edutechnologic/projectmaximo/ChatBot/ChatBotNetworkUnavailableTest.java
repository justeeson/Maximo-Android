package app.edutechnologic.projectmaximo.ChatBot;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.Assert;

import app.edutechnologic.projectmaximo.R;

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

    /**
     * This test is just to make sure we can attempt to send a message without crashing the app.
     */
    @Test
    public void sendMessageTest() {
        ChatBotActivity chatBotActivity = mActivityTestRule.getActivity();
        chatBotActivity = mActivityTestRule.launchActivity(new Intent());
        Assert.assertNotNull(chatBotActivity);

        // Make sure we can send a message without crashing.
        // Find text box and enter text
        TextView messageEntryBox = chatBotActivity.findViewById(R.id.chat_message_text);
        Assert.assertNotNull("Could not find chat_message_text", messageEntryBox);
        messageEntryBox.setText("Hello");

        // Find send button and click
        Button sendButton = chatBotActivity.findViewById(R.id.chat_send_button);
        Assert.assertTrue("Could not find chat_send_button", sendButton.isClickable());
        sendButton.performClick();
    }
}
