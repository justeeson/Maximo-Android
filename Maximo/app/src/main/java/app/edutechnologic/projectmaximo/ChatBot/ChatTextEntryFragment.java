package app.edutechnologic.projectmaximo.ChatBot;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.edutechnologic.projectmaximo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnMessageSendListener} interface
 * to handle interaction events.
 * Use the {@link ChatTextEntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatTextEntryFragment
        extends Fragment {
    private OnMessageSendListener mListener;

    public ChatTextEntryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChatTextEntryFragment.
     */
    public static ChatTextEntryFragment newInstance() {
        return new ChatTextEntryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_text_entry_view, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMessageSendListener) {
            mListener = (OnMessageSendListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMessageSendListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMessageSendListener {
        void onMessageSend(ChatMessage message);
    }
}
