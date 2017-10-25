package app.edutechnologic.projectmaximo.ChatBot;

import android.content.ContentValues;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.edutechnologic.projectmaximo.FeedReaderContract;
import app.edutechnologic.projectmaximo.R;

/**
 * View which represents a chat layout.
 */
public class ChatMessageView extends ConstraintLayout {
    private String messageAsString;
    private long dateInMilliseconds;
    private String date;

    public ChatMessageView(Context context) {
        super(context);
        initView();
    }

    /**
     * Instantiates a ChatMessageView representing a given message.
     *
     * @param context the context from which the ChatMessageView object was created
     * @param message the message to represent with the view.
     */
    public ChatMessageView(Context context, @NotNull ChatMessage message) {
        super(context);
        Date now = new Date();
        this.messageAsString = message.getMessage();
        dateInMilliseconds = System.currentTimeMillis();

        initView();
        if (message.getIsResponse()) {
            makeResponse();
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, y-M-d h:ma");
        date = dateFormatter.format(now);


        String chatMessage = message.getMessage();

        String finalMessage = date + "\t" + chatMessage;

        SpannableStringBuilder cs = new SpannableStringBuilder(finalMessage);
        cs.setSpan(new SubscriptSpan(), 0, date.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs.setSpan(new RelativeSizeSpan(0.50f), 0, date.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(cs);
    }

    /**
     * Sets the initial view
     */
    private void initView() {
        inflate(getContext(), R.layout.chatbot_chatmessage_view, this);
    }

    /**
     * Adjusts the styling of this chat message to be a request to the chat bot.
     * Also adds the conversation to the history.
     */
    public void makeRequest() {
        setColor(R.color.colorChatRequest);
        setHorizontalAlignment(ConstraintSet.RIGHT);
        // Add the message to the conversation history
        ContentValues values = new ContentValues();
        values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_USERTYPE, "user");
        values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_MESSAGE, messageAsString);
        values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_TIMESTAMP, dateInMilliseconds);
        ChatBotActivity.chatDbWriteable.insert(ChatBotHistoryContract.ChatBotHistoryEntry.TABLE_NAME, null, values);
    }

    /**
     * Adjusts the styling of this chat message to be a response to the chat bot.
     */
    public void makeResponse() {
        setColor(R.color.colorChatResponse);
        setHorizontalAlignment(ConstraintSet.LEFT);
        // Add the message to the conversation history
        ContentValues values = new ContentValues();
        values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_USERTYPE, "bot");
        values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_MESSAGE, messageAsString);
        values.put(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_TIMESTAMP, dateInMilliseconds);
        ChatBotActivity.chatDbWriteable.insert(ChatBotHistoryContract.ChatBotHistoryEntry.TABLE_NAME, null, values);
    }

    /**
     * Sets the text of the TextView object
     *
     * @param spannableString SpannableString to insert into the TextView
     */
    private void setText(SpannableStringBuilder spannableString) {
        TextView text = this.findViewById(R.id.chat_message_text);
        text.setText(spannableString);
    }

    /**
     * Sets the color of the message box
     *
     * @param color the color to set the message box background to
     */
    private void setColor(int color) {
        // Set the background color for the text box.
        View msgText = this.findViewById(R.id.chat_message_text);
        msgText.setBackgroundColor(ContextCompat.getColor(getContext(), color));
    }

    /**
     * Sets the alignment of the chat view
     *
     * @param alignment the passed alignment value
     */
    private void setHorizontalAlignment(int alignment) {
        // TODO: Remove this line. Used for testing...
        // FIXME: This method does not appear to do anything.
        alignment = ConstraintSet.LEFT;

        int id = R.id.chat_message_text;

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clear(id, ConstraintSet.RIGHT);
        constraintSet.clear(id, ConstraintSet.LEFT);

        constraintSet.connect(id, alignment, ConstraintSet.PARENT_ID, alignment);
        constraintSet.applyTo(this);

    }
}
