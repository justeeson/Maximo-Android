package edu.osu.cse.projectmaximo.ChatBot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import java.util.GregorianCalendar;

import edu.osu.cse.projectmaximo.R;

public class ChatBotActivity extends AppCompatActivity
implements ChatTextEntryFragment.OnMessageSendListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
    }

    /**
     * Handles adding the message to the message history, and sending
     * @param message The text of the message being added to the chat history.
     */
    @Override
    public void onMessageSend(ChatMessage message) {
        // TODO: Update this to add text to chat history and save in the database.
        // Place a message in chat history UI.
        boolean sendMessage = message != null && message.getMessage() != null
                && !message.getMessage().isEmpty();
        if (sendMessage) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            ChatMessageFragment msg = ChatMessageFragment.newInstance(message);

            transaction.add(R.id.chat_message_history, msg);
            transaction.commitNow();
        }
    }

    public void onSendButtonPressed(View view) {
        // Get the message from the EditText
        EditText messageBox = findViewById(R.id.messageBox);
        String message = messageBox.getText().toString();
        messageBox.setText("");

        ChatMessage chat = new ChatMessage(message, (new GregorianCalendar()).getTimeInMillis());
        onMessageSend(chat);
        scrollToMostRecentMessage();
    }

    private void scrollToMostRecentMessage() {
        // Scroll to bottom
        ScrollView scrollView = findViewById(R.id.chat_scroll_view);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}
