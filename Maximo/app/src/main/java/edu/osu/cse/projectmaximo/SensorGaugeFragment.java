package edu.osu.cse.projectmaximo;

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

        /**
         * Set the value for the sensor gauge.
         */
        View view = inflater.inflate(R.layout.fragment_sensor_gauge, container, false);
        CustomGauge gauge = view.findViewById(R.id.gauge);
        TextView gaugeText = view.findViewById(R.id.gaugeText);
        //value is temporarily hard-coded in
        gauge.setValue(500);
        Integer gaugeValue = gauge.getValue();
        gaugeText.setText(getString(R.string.gauge_val, gaugeValue));
        return view;
    }
}
