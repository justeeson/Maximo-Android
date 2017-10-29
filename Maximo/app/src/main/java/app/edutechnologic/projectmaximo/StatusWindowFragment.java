package app.edutechnologic.projectmaximo;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
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
        sensorOne.setText(Maximo.sensorGaugeNames.get(0));
        TextView sensorTwo = view.findViewById(R.id.sensorTwo);
        sensorTwo.setText(Maximo.sensorGaugeNames.get(1));

        //Fill in operational status of each sensor by checking the operational status in the DB
        //Sensor 1
        TextView statusOne = view.findViewById(R.id.statusOne);
        String statusOneText;
        if (Maximo.sensorGaugeOpStatuses.get(0) == 1) {
            //operational = green text, down = red text
            statusOneText = "Operational";
            statusOne.setTextColor(Color.parseColor("#008000"));
        } else {
            statusOneText = "Down";
            statusOne.setTextColor(Color.parseColor("#FF0000"));
        }
        statusOne.setText(statusOneText);
        //Sensor 2
        TextView statusTwo = view.findViewById(R.id.statusTwo);
        String statusTwoText;
        if (Maximo.sensorGaugeOpStatuses.get(1) == 1) {
            //operational = green text, down = red text
            statusTwoText = "Operational";
            statusTwo.setTextColor(Color.parseColor("#008000"));
        } else {
            statusTwoText = "Down";
            statusTwo.setTextColor(Color.parseColor("#FF0000"));
        }
        statusTwo.setText(statusTwoText);

        return view;
    }

}
