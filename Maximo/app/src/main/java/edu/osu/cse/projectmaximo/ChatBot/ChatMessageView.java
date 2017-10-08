package edu.osu.cse.projectmaximo.ChatBot;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.view.View;

import edu.osu.cse.projectmaximo.R;

/**
 * View which represents a chat layout.
 */
public class ChatMessageView extends ConstraintLayout {
    public ChatMessageView(Context context) {
        super(context);
        initView();
    }

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
     * Adjusts the styling of this chat message to be a respone to the chat bot.
     */
    public void makeResponse() {
        setColor(R.color.colorChatResponse);
        setHorizontalAlignment(ConstraintSet.LEFT);
    }

    private void setColor(int color) {
        // Set the background color for the text box.
        View msgText = this.findViewById(R.id.chat_message_text);
        msgText.setBackgroundColor(ContextCompat.getColor(getContext(), color));
    }

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
