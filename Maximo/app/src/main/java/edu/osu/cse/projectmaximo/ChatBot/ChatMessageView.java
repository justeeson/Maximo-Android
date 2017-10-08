package edu.osu.cse.projectmaximo.ChatBot;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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
    }

    /**
     * Adjusts the styling of this chat message to be a respone to the chat bot.
     */
    public void makeResponse() {
        setColor(R.color.colorChatResponse);

    }

    private void setColor(int color) {
        // Should have only one child, but just in case.
        for (int i = 0; i < this.getChildCount(); i++) {
            // Set chat_message_text color.
            View child = this.getChildAt(i);
            if (child.getId() == R.id.chat_message_text) {
                child.setBackgroundColor(color);
            }
        }
    }
}
