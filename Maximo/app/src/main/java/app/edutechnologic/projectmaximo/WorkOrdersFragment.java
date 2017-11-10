package app.edutechnologic.projectmaximo;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alex on 10/26/2017.
 * Fragment used for displaying the work orders table.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WorkOrdersFragment extends Fragment {

    private TableLayout tableLayout;
    private final ArrayList<String> work_order_number = new ArrayList<>();
    private final ArrayList<String> work_order_descriptions = new ArrayList<>();
    private final ArrayList<String> work_order_assetnumbers = new ArrayList<>();
    private final ArrayList<String> work_order_locations = new ArrayList<>();
    private final ArrayList<String> work_order_reporteddates = new ArrayList<>();
    private final ArrayList<String> work_order_statuses = new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_work_orders_table, container, false);
        tableLayout = view.findViewById(R.id.workOrdersTable);

        readData();

        final TableRow[] tableRow = new TableRow[work_order_number.size()];
        final TextView[] textView = new TextView[6];
        for ( int i = 0; i < work_order_number.size(); i++) {
            tableRow[i] = new TableRow(getActivity());
            tableRow[i].setBackgroundResource(R.drawable.border2);
            final int m = i;
            // set text for columns
            textView[0] = new TextView(getActivity());
            textView[0].setText(work_order_number.get(i));
            textView[0].setGravity(Gravity.CENTER);
            textView[0].setTextColor(Color.BLUE);
            // work order name is clickable
            textView[0].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    viewTasks(v);
                }
            });
            tableRow[m].addView(textView[0], new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            textView[1] = new TextView(getActivity());
            textView[1].setText(work_order_descriptions.get(i));
            textView[1].setGravity(Gravity.CENTER);
            textView[1].setTextColor(Color.BLACK);
            // click to change descriptions
            textView[1].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    switchToEditText(v);

                }
            });
            // add this column to the current row
            tableRow[m].addView(textView[1], new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            textView[2] = new TextView(getActivity());
            textView[2].setText(work_order_assetnumbers.get(i));
            textView[2].setGravity(Gravity.CENTER);
            textView[2].setTextColor(Color.BLACK);
            // click to change asset
            textView[2].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    switchToEditText(v);
                }
            });

            // add this column to the current row
            tableRow[m].addView(textView[2], new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            textView[3] = new TextView(getActivity());
            textView[3].setText(work_order_locations.get(i));
            textView[3].setGravity(Gravity.CENTER);
            textView[3].setTextColor(Color.BLACK);
            // click to change location
            textView[3].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    switchToEditText(v);
                }
            });
            // add this column to the current row
            tableRow[m].addView(textView[3], new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));



            textView[4] = new TextView(getActivity());
            textView[4].setText(work_order_reporteddates.get(i));
            textView[4].setGravity(Gravity.CENTER);
            textView[4].setTextColor(Color.BLACK);
            // click to change reported date
            textView[4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    switchToEditText(v);
                }
            });
            // add this column to the current row
            tableRow[m].addView(textView[4], new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));



            textView[5] = new TextView(getActivity());
            textView[5].setText(work_order_statuses.get(i));
            textView[5].setGravity(Gravity.CENTER);
            textView[5].setTextColor(Color.BLACK);
            // click to change status
            textView[5].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    switchToEditText(v);
                }
            });
            // add this column to the current row
            tableRow[m].addView(textView[5], new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            // add columns in the current row
//            for (int j = 0; j < 6; j++) {
//                tableRow[i].addView(textView[j], new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
//            }

            // add current in the table
            tableLayout.addView(tableRow[i]);
        }

        // save button
        TableRow tableRowForBtn = new TableRow(getActivity());
        Button saveWorkOrdersBtn = new Button(getActivity());
        saveWorkOrdersBtn.setText("Save");
        saveWorkOrdersBtn.setId(R.id.SaveWorkOrders);

        tableRowForBtn.addView(saveWorkOrdersBtn, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        tableLayout.addView(tableRowForBtn);

        saveWorkOrdersBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // refresh current page
            }
        });

        return view;
    }

    public ViewGroup getParent(View view) {
        return (ViewGroup)view.getParent();
    }

    public void removeView(View view) {
        ViewGroup parent = getParent(view);
        if(parent != null) {
            parent.removeView(view);;
        }
    }

    public void replaceView(View currentView, View newView) {
        ViewGroup parent = getParent(currentView);
        if(parent == null) {
            return;
        }
        final int index = parent.indexOfChild(currentView);
        removeView(currentView);
        // removeView(newView);
        parent.addView(newView, index);
    }

    /**
     * switch the current textview to an edit text
     * */
    public void switchToEditText(View view){
        // setup for new edit text
        TextView textView = (TextView)view;
        String content = textView.getText().toString();
        //textView.setVisibility(View.GONE);
        EditText editText = new EditText(getActivity());
        editText.setText(content);

        // replace view
        replaceView(textView, editText);


    }
    /** Called when the user clicks a work order number to view its tasks */
    public void viewTasks(View view) {
        //create intent to go to the work order task page and pass in the work order number
        Intent intent = new Intent(view.getContext(), WorkOrderTasksActivity.class);
        TextView text = (TextView) view;
        String workOrderNumber = text.getText().toString();
        intent.putExtra("num", workOrderNumber);
        startActivity(intent);
    }

    public void readData() {
        WorkOrderDbHelper wDbHelper = new WorkOrderDbHelper(getActivity());
        SQLiteDatabase wdbReadable = wDbHelper.getReadableDatabase();

        // read work orders

        // Cursor cursor = wdbReadable.rawQuery("select " + WorkOrderContract.WorkOrderEntry.COLUMN_NAME_NUMBER + "from " + WorkOrderContract.WorkOrderEntry.TABLE_NAME, null);
        Cursor cursor = wdbReadable.rawQuery("select * from " + WorkOrderContract.WorkOrderEntry.TABLE_NAME, null);

        //work_order_number = new ArrayList<String> ();
        while (cursor.moveToNext()) {
            String item_number = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_NUMBER));
            work_order_number.add(item_number);

            String item_descriptions = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_DESCRIPTION));
            work_order_descriptions.add(item_descriptions);

            String item_assetnumbers = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_ASSETNUMBER));
            work_order_assetnumbers.add(item_assetnumbers);

            String item_locations = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_LOCATION));
            work_order_locations.add(item_locations);

            String item_reporteddates = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_REPORTEDDATE));
            work_order_reporteddates.add(item_reporteddates);

            String item_statuses = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_STATUS));
            work_order_statuses.add(item_statuses);

        }

        cursor.close();
    }
}
