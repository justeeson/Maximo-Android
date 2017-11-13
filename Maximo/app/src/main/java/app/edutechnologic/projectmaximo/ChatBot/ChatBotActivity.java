package app.edutechnologic.projectmaximo.ChatBot;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.jetbrains.annotations.NotNull;

import java.util.GregorianCalendar;

import app.edutechnologic.projectmaximo.BottomMenuBar;
import app.edutechnologic.projectmaximo.R;

public class ChatBotActivity extends AppCompatActivity
        implements ChatTextEntryFragment.OnMessageSendListener {
    private static Activity appActivity;
    private static Context appContext;
    private Boolean recordingStatus = false;
    private static EditText messageBox;
    public static ChatBotHistoryDbHelper chatDbHelper;
    private static SQLiteDatabase chatDbWriteable;
    private static final Handler UIHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        appActivity = this;
        appContext = getApplicationContext();

        android.support.v7.widget.AppCompatImageButton micButton;

        //Set up the database for chat bot message history
        chatDbHelper = new ChatBotHistoryDbHelper(getApplicationContext());

        // Gets the data repository in write mode
        chatDbWriteable = chatDbHelper.getWritableDatabase();

        //bottom navbar menu button functionality
        final Button homeButton = findViewById(R.id.home_nav_btn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
        final Button workOrdersButton = findViewById(R.id.work_orders_nav_btn);
        workOrdersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });

        messageBox = this.findViewById(R.id.chat_message_entry_box);

        micButton = this.findViewById(R.id.chat_btn_record);
        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatBotHandler.speechToText(recordingStatus, messageBox);
                recordingStatus = !recordingStatus;
                if (!recordingStatus) {
                    getUserMessageAndTimeStamp();
                }
            }
        });
        ChatBotHandler.initialize();
        ChatBotConversationHistory.fetchHistory();
        this.scrollToMostRecentMessage();
    }

    /**
     * Handles adding the message to the message history, and sending.
     *
     * @param message The text of the message being added to the chat history.
     */
    @Override
    public void onMessageSend(@NotNull ChatMessage message) {
        if(ChatBotHandler.checkInternetConnection()) {
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
                    public void onClick(View v) {
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
                    public void onClick(View v) {
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
    }

    /**
     * This function is invoked when the send button is pressed. It calls another function
     * that handles the logic for retrieving the user message and sending it to Watson.
     *
     * @param view the view that invoked this function on being pressed.
     */
    public void onSendButtonPressed(View view) {
        getUserMessageAndTimeStamp();
    }

    /**
     * This function extracts the user's message and timestamp and then calls another function
     * that handles the final logic for interacting with the Watson API.
     */
    private void getUserMessageAndTimeStamp() {
        // Get the message from the EditText
        EditText messageBox = findViewById(R.id.chat_message_entry_box);
        String message = messageBox.getText().toString();
        messageBox.setText("");

        ChatMessage chat = new ChatMessage(message, (new GregorianCalendar()).getTimeInMillis());
        onMessageSend(chat);
        scrollToMostRecentMessage();
    }

    /**
     * This function lets the user scroll immediately to the most recent message in the conversation history.
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

    /**
     * This function is responsible for updating the message box
     *
     * @param text The text to be set in the message box.
     */
    public static void updateMessageBox(String text) {
        final String messageText = text;
        UIHandler.post(new Runnable() {
            @Override
            public void run() {
                messageBox.setText(messageText);
            }
        });
    }

    /**
     * This function returns an instance of this class.
     *
     * @return appActivity An instance of the class.
     */
    public static Activity getActivity(){
        return appActivity;
    }

    /**
     * This function returns the application context.
     *
     * @return appContext The application context.
     */
    public static Context getAppContext(){
        return appContext;
    }
}
