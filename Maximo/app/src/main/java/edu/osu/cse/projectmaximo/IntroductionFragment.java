package edu.osu.cse.projectmaximo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Alex on 9/17/2017.
 * Fragment used for displaying introduction message.
 */

public class IntroductionFragment extends Fragment {

    /**
     * The fragment argument representing the hour of the day.
     */
    //private static final int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Get current hour of the day and change the greeting text based on the time
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String greeting;
        if (currentHour >= 0 && currentHour < 12) {
            greeting = "morning";
        } else if (currentHour >= 12 && currentHour < 17) {
            greeting = "afternoon";
        } else {
            greeting = "evening";
        }

        //Set the greeting text in the introduction pane
        View view = inflater.inflate(R.layout.fragment_introduction, container, false);
        TextView textView = view.findViewById(R.id.introTextView);
        textView.setText(getString(R.string.introduction, greeting, Maximo.userIdentity));
        return view;
    }
}
