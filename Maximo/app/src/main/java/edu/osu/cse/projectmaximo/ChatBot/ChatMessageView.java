package edu.osu.cse.projectmaximo.ChatBot;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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


}
