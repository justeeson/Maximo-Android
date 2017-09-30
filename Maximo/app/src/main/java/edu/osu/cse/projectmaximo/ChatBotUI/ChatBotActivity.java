package edu.osu.cse.projectmaximo.ChatBotUI;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import edu.osu.cse.projectmaximo.R;

public class ChatBotActivity extends AppCompatActivity
implements ChatTextEntryView.OnMessageSendListener {


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
    public void onMessageSend(String message) {
        // TODO: Update this to add text to chat history and save in the database.
        // Place a message in chat history.
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        ChatMessage msg = ChatMessage.newInstance(message);
        //msg.setText("Hello");

        transaction.add(R.id.chat_message_history, msg);
        transaction.commit();

    }

    public void onSendButtonPressed(View view) {
        // Get the message from the EditText
        EditText messageBox = (EditText) findViewById(R.id.messageBox);
        String message = messageBox.getText().toString();
        messageBox.setText("");
        onMessageSend(message);
    }
}
