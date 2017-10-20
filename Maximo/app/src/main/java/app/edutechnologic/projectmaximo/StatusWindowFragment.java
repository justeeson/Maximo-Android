package app.edutechnologic.projectmaximo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Alex on 10/4/2017.
 * Fragment used for displaying various sensors and their operational status.
 * (Temporarily hard-coded in - must change based on number of sensors)
 */

public class StatusWindowFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Fill in the status window table with all sensors and their operational status
        View view = inflater.inflate(R.layout.fragment_status_window, container, false);
        //Get sensor names and insert into table
        TextView sensorOne = view.findViewById(R.id.sensorOne);
        sensorOne.setText(getString(R.string.empty_string, "Sensor 1"));
        TextView sensorTwo = view.findViewById(R.id.sensorTwo);
        sensorTwo.setText(getString(R.string.empty_string, "Sensor 2"));

        //Get operational status of each sensor

        //Fill in operational status of each sensor
        TextView statusOne = view.findViewById(R.id.statusOne);
        statusOne.setText(getString(R.string.empty_string, "Operational"));
        TextView statusTwo = view.findViewById(R.id.statusTwo);
        statusTwo.setText(getString(R.string.empty_string, "Down"));

        return view;
    }

}
