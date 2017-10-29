package app.edutechnologic.projectmaximo.ChatBot;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.jetbrains.annotations.NotNull;

import java.util.GregorianCalendar;

import app.edutechnologic.projectmaximo.BottomMenuBar;
import app.edutechnologic.projectmaximo.MaximoUtility;
import app.edutechnologic.projectmaximo.R;

public class ChatBotActivity extends AppCompatActivity
implements ChatTextEntryFragment.OnMessageSendListener {
    public static Activity appActivity;
    public static Context appContext;
    private android.support.v7.widget.AppCompatImageButton micButton;
    private Boolean recordingStatus = false;
    public static EditText messageBox;
    public static ChatBotHistoryDbHelper chatDbHelper;
    public static SQLiteDatabase chatDbWriteable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        MaximoUtility.initialize();
        ChatBotHandler.initialize();

        //Set up the database for chat bot message history
        chatDbHelper = new ChatBotHistoryDbHelper(getApplicationContext());

        // Gets the data repository in write mode
        chatDbWriteable = chatDbHelper.getWritableDatabase();

        //bottom navbar menu button functionality
        final Button mediaButton = findViewById(R.id.dashboard_nav_btn);
        mediaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
        final Button homeButton = findViewById(R.id.home_nav_btn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });

        appActivity = this;
        appContext = getApplicationContext();
        messageBox = (EditText) this.findViewById(R.id.messageBox);
        /*
        micButton = (android.support.v7.widget.AppCompatImageButton) this.findViewById(R.id.btn_record);
        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    ChatBotHandler.speechToText(recordingStatus, messageBox);
                    recordingStatus = !recordingStatus;
            }
        });
        */
        ChatBotConversationHistory.fetchHistory();
        this.scrollToMostRecentMessage();
    }

    /**
     * Handles adding the message to the message history, and sending
     * @param message The text of the message being added to the chat history.
     */
    @Override
    public void onMessageSend(@NotNull ChatMessage message) {
        // TODO: Update this to add text to chat history and save in the database.
        // Place a message in chat history UI.
        boolean sendMessage = message.getMessage() != null
                && !message.getMessage().isEmpty();
        if (sendMessage) {
            final String messageAsString = message.getMessage();

            // Add the message to the conversation history
                ContentValues values = new ContentValues();
                values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_USERTYPE, "user");
                values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_MESSAGE, messageAsString);
                values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_TIMESTAMP, System.currentTimeMillis());
                chatDbWriteable.insert(ChatBotHistoryContract.ChatBotHistoryEntry.TABLE_NAME, null, values);

            // Add message to convo history
            LinearLayout convoHistory = findViewById(R.id.chat_message_history);
            ChatMessageView view = new ChatMessageView(this, message);

            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    ChatBotHandler.textToSpeech(messageAsString);
                }
            });
            convoHistory.addView(view);


            // TODO: Make this async
            // Get the message from the chat bot handler.
            final String response = ChatBotHandler.sendMessage(message.getMessage());
            ChatMessageView responseView = new ChatMessageView(this, new ChatMessage(response));

            responseView.setClickable(true);
            responseView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    ChatBotHandler.textToSpeech(response);
                }
            });

            // Add the response to conversation history
            values = new ContentValues();
            values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_USERTYPE, "bot");
            values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_MESSAGE, response);
            values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_TIMESTAMP, System.currentTimeMillis());
            chatDbWriteable.insert(ChatBotHistoryContract.ChatBotHistoryEntry.TABLE_NAME, null, values);

            responseView.makeResponse();
            convoHistory.addView(responseView);
        }
    }

    /**
     * This function handles bot response when send button is pressed
     * @param view the view that invoked this function on being pressed
     */
    public void onSendButtonPressed(View view) {
        // Get the message from the EditText
        EditText messageBox = findViewById(R.id.messageBox);
        String message = messageBox.getText().toString();
        messageBox.setText("");

        ChatMessage chat = new ChatMessage(message, (new GregorianCalendar()).getTimeInMillis());
        onMessageSend(chat);
        scrollToMostRecentMessage();
    }

    /**
     * This function lets the user scroll immediately to the most recent message in the conversation history
     */
    private void scrollToMostRecentMessage() {
        // Scroll to bottom.
        final ScrollView scrollView = findViewById(R.id.chat_scroll_view);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
