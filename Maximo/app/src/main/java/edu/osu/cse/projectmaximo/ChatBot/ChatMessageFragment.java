package edu.osu.cse.projectmaximo.ChatBot;

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

    // Text to display for the message.
    private String mMessageText;

    public ChatMessageFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a new instance of ChatMessageFragment which displays the text passed.
     *
     * @param msg Text to display on the message.
     * @return A new instance of fragment ChatMessageFragment.
     */
    public static ChatMessageFragment newInstance(String msg) {
        ChatMessageFragment fragment = new ChatMessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE_TEXT, msg);
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
            mMessageText = getArguments().getString(ARG_MESSAGE_TEXT);
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
