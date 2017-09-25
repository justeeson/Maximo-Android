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

    @Override
    public void onMessageSend(String message) {
        // TODO
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onSendButtonPressed(View view) {
        // Get the message from the EditText
        EditText messageBox = (EditText) findViewById(R.id.messageBox);
        onMessageSend(messageBox.getText().toString());
    }
}
