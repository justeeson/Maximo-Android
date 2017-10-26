package app.edutechnologic.projectmaximo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Alex on 10/26/2017.
 * Fragment used for displaying the work orders table.
 */

public class WorkOrdersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_work_orders_table, container, false);

        //Populate work orders table

        return view;
    }
}
