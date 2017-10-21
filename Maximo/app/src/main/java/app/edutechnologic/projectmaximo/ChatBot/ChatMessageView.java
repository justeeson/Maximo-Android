package app.edutechnologic.projectmaximo.ChatBot;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import app.edutechnologic.projectmaximo.R;

/**
 * View which represents a chat layout.
 */
public class ChatMessageView extends ConstraintLayout {
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
        initView();
        if (message.getIsResponse()) {
            makeResponse();
        }
        setText(message.getMessage());
    }

    /**
     * Sets the initial view
     */
    private void initView() {
        inflate(getContext(), R.layout.chatbot_chatmessage_view, this);
    }

    /**
     * Adjusts the styling of this chat message to be a request to the chat bot.
     */
    public void makeRequest() {
        setColor(R.color.colorChatRequest);
        setHorizontalAlignment(ConstraintSet.RIGHT);
    }

    /**
     * Adjusts the styling of this chat message to be a response to the chat bot.
     */
    public void makeResponse() {
        setColor(R.color.colorChatResponse);
        setHorizontalAlignment(ConstraintSet.LEFT);
    }

    /**
     * Sets the text of the TextView object
     *
     * @param message string to insert into the TextView
     */
    private void setText(String message) {
        TextView text = this.findViewById(R.id.chat_message_text);
        text.setText(message);
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
