package app.edutechnologic.projectmaximo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.pawelkleczkowski.customgauge.CustomGauge;

/**
 * Created by Alex on 10/2/2017.
 * Fragment used for displaying a sensor gauge.
 */

public class SensorGaugeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Set the value for the sensor gauge.
        View view = inflater.inflate(R.layout.fragment_sensor_gauge, container, false);
        //find views that will be updated (find by ID set in XML doc)
        CustomGauge gauge = view.findViewById(R.id.gauge);
        TextView gaugeText = view.findViewById(R.id.gaugeText);
        TextView gaugeName = view.findViewById(R.id.gaugeName);

        /*
            Get the sensor gauge value percentage [(actualValue * 100) /totalValue] in order to
            animate the sensor gauge (sensor gauge value is between 0-100). In this case we are
            showing the values for sensor 1 (index 0).
         */
        int sensorValuePercentage = (Maximo.sensorGaugeActualVals.get(0) * 100) / Maximo.sensorGaugeTotalVals.get(0);
        gauge.setValue(sensorValuePercentage);

        //Display the name and actual value for the sensor
        gaugeName.setText(Maximo.sensorGaugeNames.get(0));
        gaugeText.setText(getString(R.string.val, Maximo.sensorGaugeActualVals.get(0)));
        return view;
    }
}
