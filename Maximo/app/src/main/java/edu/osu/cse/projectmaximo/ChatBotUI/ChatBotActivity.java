package edu.osu.cse.projectmaximo.ChatBotUI;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public void onSendButtonPressed(View view) {
        // Get the message from the EditText
        EditText messageBox = (EditText) findViewById(R.id.messageBox);
        String message = messageBox.getText().toString();
        messageBox.setText("");
        onMessageSend(message);
    }
}
