package edu.osu.cse.projectmaximo.ChatBot;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.osu.cse.projectmaximo.R;

/**
 * The UI element that represents a message in chat.
 */
public class ChatMessageFragment extends Fragment {
    // Fragment parameters for message text
    private static final String ARG_MESSAGE_TEXT = "message_text";
    private static final String ARG_TIME = "message_time";
    private static final String ARG_IS_RESPONSE = "message_is_response";

    // Text to display for the message.
    private String mMessageText;
    private boolean mIsResponse = false;

    public ChatMessageFragment() {
        // Required empty public constructor
    }

    public static ChatMessageFragment newInstance(ChatMessage message) {
        ChatMessageFragment fragment = new ChatMessageFragment();
        Bundle args = new Bundle();

        // Set arguments from message.
        args.putString(ARG_MESSAGE_TEXT, message.getMessage());
        args.putLong(ARG_TIME, message.getTime());
        args.putBoolean(ARG_IS_RESPONSE, message.getIsResponse());

        fragment.setArguments(args);
        return fragment;
    }

    public void setText(String text) {
        TextView message = getView().findViewById(R.id.chat_message_text);
        message.setText(text);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            mMessageText = args.getString(ARG_MESSAGE_TEXT);
            long timeInMillis = args.getLong(ARG_TIME);
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(timeInMillis);
            Date mTime = cal.getTime();
            mIsResponse = args.getBoolean(ARG_IS_RESPONSE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_message, container, false);

        TextView messageText = view.findViewById(R.id.chat_message_text);
        messageText.setText(mMessageText);

        return view;
    }
}
