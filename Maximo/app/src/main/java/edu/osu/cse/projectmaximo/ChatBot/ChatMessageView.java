package edu.osu.cse.projectmaximo.ChatBot;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import edu.osu.cse.projectmaximo.R;

/**
 * Created by jonms on 10/7/2017.
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
        setAlignment(ConstraintSet.RIGHT);
    }

    /**
     * Adjusts the styling of this chat message to be a respone to the chat bot.
     */
    public void makeResponse() {
        setColor(R.color.colorChatResponse);
        setAlignment(ConstraintSet.LEFT);
    }

    private void setColor(int color) {
        // Set the background color for the text box.
        View msgText = this.findViewById(R.id.chat_message_text);
        msgText.setBackgroundColor(color);
    }

    private void setAlignment(int alignment) {
        View msgText = this.findViewById(R.id.chat_message_text);

        // TODO: Test this.
        int endSide = alignment == ConstraintSet.LEFT ? ConstraintSet.RIGHT : ConstraintSet.LEFT;

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.connect(msgText.getId(), alignment, ConstraintSet.PARENT_ID, endSide);

    }
}
