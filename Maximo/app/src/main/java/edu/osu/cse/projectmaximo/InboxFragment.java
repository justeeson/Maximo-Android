package edu.osu.cse.projectmaximo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Alex on 9/21/2017.
 * Fragment used for displaying the user's inbox.
 */

public class InboxFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         * Set the text in the first row of the inbox. Temporarily hard-coded in until
         * we get access to Maximo and are able to display messages.
         */
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        TextView textView = view.findViewById(R.id.rowOneTextView);
        textView.setText(getString(R.string.empty_inbox, Maximo.userIdentity));
        return view;
    }
}
