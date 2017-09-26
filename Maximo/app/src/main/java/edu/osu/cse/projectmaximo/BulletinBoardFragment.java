package edu.osu.cse.projectmaximo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Alex on 9/24/2017.
 * Fragment used for displaying the user's company-wide bulletin board.
 */

public class BulletinBoardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         * Set the text in the first row of the bulletin board. Temporarily hard-coded in until
         * we get access to Maximo and are able to display announcements.
         */
        View view = inflater.inflate(R.layout.fragment_bulletin_board, container, false);
        TextView textView = view.findViewById(R.id.RowOne);
        textView.setText(getString(R.string.empty_bulletin_board));
        return view;
    }
}
