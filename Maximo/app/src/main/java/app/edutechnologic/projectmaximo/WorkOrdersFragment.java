package app.edutechnologic.projectmaximo;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Alex on 10/26/2017.
 * Fragment used for displaying the work orders table.
 */

public class WorkOrdersFragment extends Fragment {

    TableLayout tableLayout;
    TableRow tableRow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_work_orders_table, container, false);
        tableLayout = (TableLayout) view.findViewById(R.id.workOrdersTable);
        tableRow = new TableRow(getActivity());      // new row
        TextView b1 = new TextView(getActivity());
        b1.setText("I'm added dynamically");
        tableRow.addView(b1);
        tableLayout.addView(tableRow);
        //Populate work orders table

        return view;
    }

    public void readData() {
        WorkOrderDbHelper wDbHelper = new WorkOrderDbHelper(getActivity()); // 待确认
        SQLiteDatabase wdbReadable = wDbHelper.getReadableDatabase();
    }
}
